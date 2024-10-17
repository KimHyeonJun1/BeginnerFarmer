/**
* Template Name: AgriCulture
* Template URL: https://bootstrapmade.com/agriculture-bootstrap-website-template/
* Updated: Aug 07 2024 with Bootstrap v5.3.3
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/

$(document).ready(function() {
    // 각 .dropdown-toggle 클릭 시 동작
    $('.dropdown-toggle').click(function(event) {
        event.preventDefault(); // 기본 링크 동작 방지
        
        // 클릭된 메뉴의 하위 메뉴를 선택
        var $submenu = $(this).next('.dropdown-menu');

        // 이미 열려 있는 다른 모든 하위 메뉴는 닫기 (슬라이드 업)
        $('.dropdown-menu').not($submenu).slideUp();

        // 클릭한 하위 메뉴는 열기 또는 닫기 (슬라이드 토글)
        $submenu.slideToggle();
		
		// 모든 메뉴에서 active 클래스 제거
        $('.dropdown-toggle').removeClass('active');

        // 클릭된 메뉴에 active 클래스 추가
        $(this).addClass('active');
    });
	
	// 페이지 로딩 시 현재 URL에 맞는 메뉴 활성화
    var currentPath = window.location.pathname;

    // 만약 URL에 '/guide/list'가 포함되어 있으면 '농사정보' 메뉴 열기 및 '작물가이드' 활성화
    if (currentPath.includes('/guide/list')) {
        $('.dropdown-toggle:contains("농사정보")').addClass('active'); // 농사정보 메뉴 활성화
        $('.dropdown-toggle:contains("농사정보")').next('.dropdown-menu').slideDown(); // 하위 메뉴 열기
        $('.dropdown-menu a[href="/guide/list"]').addClass('active'); // 작물가이드 활성화
    }
	// 소통공간 관련 URL 처리
    if (currentPath.includes('/hr/list')) {
        $('.dropdown-toggle:contains("소통공간")').addClass('active'); // 소통공간 메뉴 활성화
        $('.dropdown-toggle:contains("소통공간")').next('.dropdown-menu').slideDown(); // 하위 메뉴 열기
        $('.dropdown-menu a[href="/hr/list"]').addClass('active'); // 게시판 활성화
    }
    // 나의 농장 관련 URL 처리
    if (currentPath.includes('/plants/list')) {
        $('.dropdown-toggle:contains("나의농장")').addClass('active'); // 나의 농장 메뉴 활성화
        $('.dropdown-toggle:contains("나의농장")').next('.dropdown-menu').slideDown(); // 하위 메뉴 열기
        $('.dropdown-menu a[href="/plants/list"]').addClass('active'); // 작물관리 활성화
    }
});

(function() {
	
  "use strict";

  /**
   * Apply .scrolled class to the body as the page is scrolled down
   */
  function toggleScrolled() {
    const selectBody = document.querySelector('body');
    const selectHeader = document.querySelector('#header');
    if (!selectHeader.classList.contains('scroll-up-sticky') && !selectHeader.classList.contains('sticky-top') && !selectHeader.classList.contains('fixed-top')) return;
    window.scrollY > 100 ? selectBody.classList.add('scrolled') : selectBody.classList.remove('scrolled');
  }

  document.addEventListener('scroll', toggleScrolled);
  window.addEventListener('load', toggleScrolled);

  /**
   * Scroll up sticky header to headers with .scroll-up-sticky class
   */
  let lastScrollTop = 0;
  window.addEventListener('scroll', function() {
    const selectHeader = document.querySelector('#header');
    if (!selectHeader.classList.contains('scroll-up-sticky')) return;

    let scrollTop = window.pageYOffset || document.documentElement.scrollTop;

    if (scrollTop > lastScrollTop && scrollTop > selectHeader.offsetHeight) {
      selectHeader.style.setProperty('position', 'sticky', 'important');
      selectHeader.style.top = `-${header.offsetHeight + 50}px`;
    } else if (scrollTop > selectHeader.offsetHeight) {
      selectHeader.style.setProperty('position', 'sticky', 'important');
      selectHeader.style.top = "0";
    } else {
      selectHeader.style.removeProperty('top');
      selectHeader.style.removeProperty('position');
    }
    lastScrollTop = scrollTop;
  });

  /**
   * Mobile nav toggle
   */
  const mobileNavToggleBtn = document.querySelector('.mobile-nav-toggle');

  function mobileNavToogle() {
    document.querySelector('body').classList.toggle('mobile-nav-active');
    mobileNavToggleBtn.classList.toggle('bi-list');
    mobileNavToggleBtn.classList.toggle('bi-x');
  }
  mobileNavToggleBtn.addEventListener('click', mobileNavToogle);

  /**
   * Hide mobile nav on same-page/hash links
   */
  document.querySelectorAll('#navmenu a').forEach(navmenu => {
    navmenu.addEventListener('click', () => {
      if (document.querySelector('.mobile-nav-active')) {
        mobileNavToogle();
      }
    });

  });

  /**
   * Toggle mobile nav dropdowns
   */
  document.querySelectorAll('.navmenu .toggle-dropdown').forEach(navmenu => {
    navmenu.addEventListener('click', function(e) {
      e.preventDefault();
      this.parentNode.classList.toggle('active');
      this.parentNode.nextElementSibling.classList.toggle('dropdown-active');
      e.stopImmediatePropagation();
    });
  });

  /**
   * Preloader
   */
  const preloader = document.querySelector('#preloader');
  if (preloader) {
    window.addEventListener('load', () => {
      preloader.remove();
    });
  }

  /**
   * Scroll top button
   */
  let scrollTop = document.querySelector('.scroll-top');

  function toggleScrollTop() {
    if (scrollTop) {
      window.scrollY > 100 ? scrollTop.classList.add('active') : scrollTop.classList.remove('active');
    }
  }
  scrollTop.addEventListener('click', (e) => {
    e.preventDefault();
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  });

  window.addEventListener('load', toggleScrollTop);
  document.addEventListener('scroll', toggleScrollTop);

  /**
   * Animation on scroll function and init
   */
  function aosInit() {
    AOS.init({
      duration: 600,
      easing: 'ease-in-out',
      once: true,
      mirror: false
    });
  }
  window.addEventListener('load', aosInit);

  /**
   * Auto generate the carousel indicators
   */
  document.querySelectorAll('.carousel-indicators').forEach((carouselIndicator) => {
    carouselIndicator.closest('.carousel').querySelectorAll('.carousel-item').forEach((carouselItem, index) => {
      if (index === 0) {
        carouselIndicator.innerHTML += `<li data-bs-target="#${carouselIndicator.closest('.carousel').id}" data-bs-slide-to="${index}" class="active"></li>`;
      } else {
        carouselIndicator.innerHTML += `<li data-bs-target="#${carouselIndicator.closest('.carousel').id}" data-bs-slide-to="${index}"></li>`;
      }
    });
  });

  /**
   * Init swiper sliders
   */
  function initSwiper() {
    document.querySelectorAll(".init-swiper").forEach(function(swiperElement) {
      let config = JSON.parse(
        swiperElement.querySelector(".swiper-config").innerHTML.trim()
      );

      if (swiperElement.classList.contains("swiper-tab")) {
        initSwiperWithCustomPagination(swiperElement, config);
      } else {
        new Swiper(swiperElement, config);
      }
    });
  }

  window.addEventListener("load", initSwiper);

  /**
   * Initiate glightbox
   */
  const glightbox = GLightbox({
    selector: '.glightbox'
  });

})();