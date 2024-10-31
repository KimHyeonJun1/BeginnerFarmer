package kr.co.farm.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.farm.member.MemberVO;

@Service

public class CommonUtility {
	
	//임시 비밀번호 이메일 보내기
	@Value("${spring.mail.host}") private String emailHost;
	@Value("${spring.mail.username}") private String emailUser;
	@Value("${spring.mail.password}") private String emailPass;
	@Value("${farm.upload}") private String uploadPath;
	
		//키의 존재유무에 따라 데이터처리하기
		public String hasKey(JSONObject json, String key) {
			return json.has(key) ? json.getString(key) : "";
		}
		//기본값을 지정해야하는 경우
		public String hasKey(JSONObject json, String key, String defaultValue) {
			return json.has(key) ? json.getString(key) : defaultValue;
		}
		
		//다중 파일 업로드
		public ArrayList<FileVO> fileUpload(String category, MultipartFile[] files
								, HttpServletRequest request) {
			ArrayList<FileVO> list = null;
			for( MultipartFile file : files) {
				if( file.isEmpty() ) continue;
				if(list == null) list = new ArrayList<FileVO>();
				FileVO vo = new FileVO();
				vo.setFilename(file.getOriginalFilename() );
				vo.setFilepath( fileUpload(category, file, request) );
				list.add(vo);
			}
			return list;
		}
		
		//단일파일 업로드
		public String fileUpload(String category, MultipartFile file
								, HttpServletRequest request) {
			// d://smart/app/upload/profile/2024/08/27
			// d://smart/app/upload/notice/2024/08/27
			// d://smart/app/upload/board/2024/08/27
			String upload = uploadPath + category 
							+ new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
			//해당 폴더의 존재유뮤를 확인해 없다면 폴더 만들기
			File dir = new File( upload );
			if( ! dir.exists() ) dir.mkdirs();
			
			// 업로드할 파일명을 고유한 id로 변경하기 : ad24-3ag-f234-adf-h.jpg
			String filename = UUID.randomUUID().toString() + "."
							+ StringUtils.getFilenameExtension(file.getOriginalFilename() );
			//클라이언트에서 선택한 파일을 서버의 영역에 물리적으로 저장
			try {
				file.transferTo(new File(upload, filename) );
			}catch(Exception e) {}
			
			// d://smart/app/upload/profile/2024/08/27/ad24-3ag-f234-adf-h.jpg
			//http://localhost/smart/upload/profile/2024/08/27/ad24-3ag-f234-adf-h.jpg
			//http://localhost/smart
			return toUrlFilePath(upload, request) + filename ;
			
		}
		
		//물리적형태 -> url형태
		// 			d://smart/app/upload/ profile/2024/08/27/ad24-3ag-f234-adf-h.jpg
		//http://localhost/smart/upload/  profile/2024/08/27/ad24-3ag-f234-adf-h.jpg
		public String toUrlFilePath(String filepath, HttpServletRequest request) {
			return filepath.replace(uploadPath, appURL(request, "/upload/"));
		}

			
	
		//회원가입축하 메시지 보내기
		public void emailForJoin(MemberVO vo) {
			 HtmlEmail sender = new HtmlEmail();
			 mailSender( sender );

			 try {
				 sender.setFrom(emailUser, "초보농부 관리자" ); //송신인
				 sender.addTo(vo.getEmail(), vo.getName() ); //수신인

				 //제목
				 sender.setSubject("초보농부 회원가입을 축하합니다");
				 //내용
				 StringBuffer content = new StringBuffer();
				 content.append("<h3><a target='_blank' href='http://192.168.0.4:8080/farm/'>초보농부 바로가기</a></h3>")
				 		.append("<div>[<strong>").append(vo.getName())
								.append("</strong>]님 회원가입을 축하합니다</div>")
						.append("<div>초보 농부에 가입해 주셔서 감사합니다</div>")
				 		;
				 sender.setHtmlMsg(content.toString() );
				 
				 sender.send();
				 
				 
			 }catch(Exception e) {
				 System.out.println(e.getMessage() );
			 }
		}
		
		
		private void mailSender(HtmlEmail sender) {
			sender.setDebug(true);
			sender.setCharset("utf-8");
	
			sender.setHostName(emailHost);
			sender.setAuthentication(emailUser, emailPass); // 이메일주소, 비번
			sender.setSSLOnConnect(true); // 로그인버튼 클릭
		}
	
	
	// 임시비밀번호 이메일 보내기
		public boolean emailForPassword(MemberVO vo, String pw) {
			boolean send = true;

			HtmlEmail sender = new HtmlEmail();
			mailSender(sender);
 
			try {
				sender.setFrom(emailUser, "초보농부 관리자"); // 이메일 보내는이 정보
				sender.addTo(vo.getEmail(), vo.getName());

				sender.setSubject("초보농부 임시비밀번호"); // 제목
				// 내용
				StringBuffer content = new StringBuffer();
				content.append("<h3>[").append(vo.getName()).append("]님 비밀번호가 발급되었습니다.</h3>");
				content.append("<div>아이디: ").append(vo.getUserid()).append("</div>");
				content.append("<div>임시 비밀번호: <strong>").append(pw).append("</strong></div>");
				content.append("<div>발급된 임시 비밀번호로 로그인 후 비밀번호를 변경하세요</dic>");
				sender.setHtmlMsg(content.toString());

				sender.send();// 보내기 버튼 클릭

			} catch (EmailException e) {
				send = false;
			}
			return send;
		}

	
	//서버 기본 URL을 생성, 반환
	public String appURL(HttpServletRequest request) {
		//http://localhost:8080/farm
		//http://127.0.0.1:80/farm
		//http://192.168.0.10:8080/farm
		StringBuffer url = new StringBuffer("http://");
		url.append(request.getServerName() ).append(":"); // localhost 또는 127.0.0.1
		url.append(request.getServerPort() ); // 8080
		url.append(request.getContextPath() ); // farm 
		return url.toString(); //최종적으로 만들어진 URL 문자열로 변환하여 반환
	}
	
	// 기본 URL에 추가적인 경로(path)를 더해 전체 URL 반환
	public String appURL(HttpServletRequest request, String path) {
		// http://127.0.0.1/farm + "/member/login"
		return new StringBuffer(appURL(request)).append(path).toString();

	}
	
	
	// Http통신API요청
	public String requestAPI(HttpURLConnection con) throws Exception {
		int responseCode = con.getResponseCode();
		BufferedReader br;
		// System.out.print("responseCode="+responseCode);
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();

		if (responseCode == 200) {
			System.out.println(res.toString()); // "{ 'a': 10, 'b': 20 }"
		}

		return res.toString();
	}

	public String requestAPI(String apiURL, String property) {
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(apiURL)).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			apiURL = requestAPI(con);
		} catch (Exception e) {
		}
		return apiURL;
	}

	public String requestAPI(String apiURL) {
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(apiURL)).openConnection();
			con.setRequestMethod("GET");
			apiURL = requestAPI(con);
		} catch (Exception e) {
		}
		return apiURL;
	}
	
	// 공공데이터 응답결과
	public JSONObject responseBody(String url) {
		JSONObject json = new JSONObject( requestAPI(url) );
		return json.getJSONObject( "response" ).getJSONObject( "body" );
	}
	
	public JSONObject response( String url ) {
		JSONObject json = new JSONObject( requestAPI(url) ).getJSONObject( "response" );
		//body가 없으면 만들어 넣기
		json.put("body", json.has("body") ?  json.getJSONObject("body") 
										  :  new JSONObject("{ totalCount: 0 }") );
		return json;
	}
	

}
