var $devicewidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
var snapper;

/*!
 * headroom.js v0.7.0 - Give your page some headroom. Hide your header until you need it
 * Copyright (c) 2014 Nick Williams - http://wicky.nillia.ms/headroom.js
 * License: MIT
 */

(function(window, document) {

    'use strict';

    /* exported features */

    var features = {
        bind : !!(function(){}.bind),
        classList : 'classList' in document.documentElement,
        rAF : !!(window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame)
    };
    window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame;

    /**
     * Handles debouncing of events via requestAnimationFrame
     * @see http://www.html5rocks.com/en/tutorials/speed/animations/
     * @param {Function} callback The callback to handle whichever event
     */
    function Debouncer (callback) {
        this.callback = callback;
        this.ticking = false;
    }
    Debouncer.prototype = {
        constructor : Debouncer,

        /**
         * dispatches the event to the supplied callback
         * @private
         */
        update : function() {
            this.callback && this.callback();
            this.ticking = false;
        },

        /**
         * ensures events don't get stacked
         * @private
         */
        requestTick : function() {
            if(!this.ticking) {
                requestAnimationFrame(this.rafCallback || (this.rafCallback = this.update.bind(this)));
                this.ticking = true;
            }
        },

        /**
         * Attach this as the event listeners
         */
        handleEvent : function() {
            this.requestTick();
        }
    };
    /**
     * Check if object is part of the DOM
     * @constructor
     * @param {Object} obj element to check
     */
    function isDOMElement(obj) {
        return obj && typeof window !== 'undefined' && (obj === window || obj.nodeType);
    }

    /**
     * Helper function for extending objects
     */
    function extend (object /*, objectN ... */) {
        if(arguments.length <= 0) {
            throw new Error('Missing arguments in extend function');
        }

        var result = object || {},
            key,
            i;

        for (i = 1; i < arguments.length; i++) {
            var replacement = arguments[i] || {};

            for (key in replacement) {
                // Recurse into object except if the object is a DOM element
                if(typeof result[key] === 'object' && ! isDOMElement(result[key])) {
                    result[key] = extend(result[key], replacement[key]);
                }
                else {
                    result[key] = result[key] || replacement[key];
                }
            }
        }

        return result;
    }

    /**
     * Helper function for normalizing tolerance option to object format
     */
    function normalizeTolerance (t) {
        return t === Object(t) ? t : { down : t, up : t };
    }

    /**
     * UI enhancement for fixed headers.
     * Hides header when scrolling down
     * Shows header when scrolling up
     * @constructor
     * @param {DOMElement} elem the header element
     * @param {Object} options options for the widget
     */
    function Headroom (elem, options) {
        options = extend(options, Headroom.options);

        this.lastKnownScrollY = 0;
        this.elem             = elem;
        this.debouncer        = new Debouncer(this.update.bind(this));
        this.tolerance        = normalizeTolerance(options.tolerance);
        this.classes          = options.classes;
        this.offset           = options.offset;
        this.scroller         = options.scroller;
        this.initialised      = false;
        this.onPin            = options.onPin;
        this.onUnpin          = options.onUnpin;
        this.onTop            = options.onTop;
        this.onNotTop         = options.onNotTop;
    }
    Headroom.prototype = {
        constructor : Headroom,

        /**
         * Initialises the widget
         */
        init : function() {
            if(!Headroom.cutsTheMustard) {
                return;
            }

            this.elem.classList.add(this.classes.initial);

            // defer event registration to handle browser
            // potentially restoring previous scroll position
            setTimeout(this.attachEvent.bind(this), 100);

            return this;
        },

        /**
         * Unattaches events and removes any classes that were added
         */
        destroy : function() {
            var classes = this.classes;

            this.initialised = false;
            this.elem.classList.remove(classes.unpinned, classes.pinned, classes.top, classes.initial);
            this.scroller.removeEventListener('scroll', this.debouncer, false);
        },

        /**
         * Attaches the scroll event
         * @private
         */
        attachEvent : function() {
            if(!this.initialised){
                this.lastKnownScrollY = this.getScrollY();
                this.initialised = true;
                this.scroller.addEventListener('scroll', this.debouncer, false);

                this.debouncer.handleEvent();
            }
        },

        /**
         * Unpins the header if it's currently pinned
         */
        unpin : function() {
            var classList = this.elem.classList,
                classes = this.classes;

            if(classList.contains(classes.pinned) || !classList.contains(classes.unpinned)) {
                classList.add(classes.unpinned);
                classList.remove(classes.pinned);
                this.onUnpin && this.onUnpin.call(this);
            }
        },

        /**
         * Pins the header if it's currently unpinned
         */
        pin : function() {
            var classList = this.elem.classList,
                classes = this.classes;

            if(classList.contains(classes.unpinned)) {
                classList.remove(classes.unpinned);
                classList.add(classes.pinned);
                this.onPin && this.onPin.call(this);
            }
        },

        /**
         * Handles the top states
         */
        top : function() {
            var classList = this.elem.classList,
                classes = this.classes;

            if(!classList.contains(classes.top)) {
                classList.add(classes.top);
                classList.remove(classes.notTop);
                this.onTop && this.onTop.call(this);
            }
        },

        /**
         * Handles the not top state
         */
        notTop : function() {
            var classList = this.elem.classList,
                classes = this.classes;

            if(!classList.contains(classes.notTop)) {
                classList.add(classes.notTop);
                classList.remove(classes.top);
                this.onNotTop && this.onNotTop.call(this);
            }
        },

        /**
         * Gets the Y scroll position
         * @see https://developer.mozilla.org/en-US/docs/Web/API/Window.scrollY
         * @return {Number} pixels the page has scrolled along the Y-axis
         */
        getScrollY : function() {
            return (this.scroller.pageYOffset !== undefined)
                ? this.scroller.pageYOffset
                : (this.scroller.scrollTop !== undefined)
                ? this.scroller.scrollTop
                : (document.documentElement || document.body.parentNode || document.body).scrollTop;
        },

        /**
         * Gets the height of the viewport
         * @see http://andylangton.co.uk/blog/development/get-viewport-size-width-and-height-javascript
         * @return {int} the height of the viewport in pixels
         */
        getViewportHeight : function () {
            return window.innerHeight
            || document.documentElement.clientHeight
            || document.body.clientHeight;
        },

        /**
         * Gets the height of the document
         * @see http://james.padolsey.com/javascript/get-document-height-cross-browser/
         * @return {int} the height of the document in pixels
         */
        getDocumentHeight : function () {
            var body = document.body,
                documentElement = document.documentElement;

            return Math.max(
                body.scrollHeight, documentElement.scrollHeight,
                body.offsetHeight, documentElement.offsetHeight,
                body.clientHeight, documentElement.clientHeight
            );
        },

        /**
         * Gets the height of the DOM element
         * @param  {Object}  elm the element to calculate the height of which
         * @return {int}     the height of the element in pixels
         */
        getElementHeight : function (elm) {
            return Math.max(
                elm.scrollHeight,
                elm.offsetHeight,
                elm.clientHeight
            );
        },

        /**
         * Gets the height of the scroller element
         * @return {int} the height of the scroller element in pixels
         */
        getScrollerHeight : function () {
            return (this.scroller === window || this.scroller === document.body)
                ? this.getDocumentHeight()
                : this.getElementHeight(this.scroller);
        },

        /**
         * determines if the scroll position is outside of document boundaries
         * @param  {int}  currentScrollY the current y scroll position
         * @return {bool} true if out of bounds, false otherwise
         */
        isOutOfBounds : function (currentScrollY) {
            var pastTop  = currentScrollY < 0,
                pastBottom = currentScrollY + this.getViewportHeight() > this.getScrollerHeight();

            return pastTop || pastBottom;
        },

        /**
         * determines if the tolerance has been exceeded
         * @param  {int} currentScrollY the current scroll y position
         * @return {bool} true if tolerance exceeded, false otherwise
         */
        toleranceExceeded : function (currentScrollY, direction) {
            return Math.abs(currentScrollY-this.lastKnownScrollY) >= this.tolerance[direction];
        },

        /**
         * determine if it is appropriate to unpin
         * @param  {int} currentScrollY the current y scroll position
         * @param  {bool} toleranceExceeded has the tolerance been exceeded?
         * @return {bool} true if should unpin, false otherwise
         */
        shouldUnpin : function (currentScrollY, toleranceExceeded) {
            var scrollingDown = currentScrollY > this.lastKnownScrollY,
                pastOffset = currentScrollY >= this.offset;

            return scrollingDown && pastOffset && toleranceExceeded;
        },

        /**
         * determine if it is appropriate to pin
         * @param  {int} currentScrollY the current y scroll position
         * @param  {bool} toleranceExceeded has the tolerance been exceeded?
         * @return {bool} true if should pin, false otherwise
         */
        shouldPin : function (currentScrollY, toleranceExceeded) {
            var scrollingUp  = currentScrollY < this.lastKnownScrollY,
                pastOffset = currentScrollY <= this.offset;

            return (scrollingUp && toleranceExceeded) || pastOffset;
        },

        /**
         * Handles updating the state of the widget
         */
        update : function() {
            var currentScrollY  = this.getScrollY(),
                scrollDirection = currentScrollY > this.lastKnownScrollY ? 'down' : 'up',
                toleranceExceeded = this.toleranceExceeded(currentScrollY, scrollDirection);

            if(this.isOutOfBounds(currentScrollY)) { // Ignore bouncy scrolling in OSX
                return;
            }

            if (currentScrollY <= this.offset ) {
                this.top();
            } else {
                this.notTop();
            }

            if(this.shouldUnpin(currentScrollY, toleranceExceeded)) {
                this.unpin();
            }
            else if(this.shouldPin(currentScrollY, toleranceExceeded)) {
                this.pin();
            }

            this.lastKnownScrollY = currentScrollY;
        }
    };
    /**
     * Default options
     * @type {Object}
     */
    Headroom.options = {
        tolerance : {
            up : 0,
            down : 0
        },
        offset : 0,
        scroller: window,
        classes : {
            pinned : 'headroom--pinned',
            unpinned : 'headroom--unpinned',
            top : 'headroom--top',
            notTop : 'headroom--not-top',
            initial : 'headroom'
        }
    };
    Headroom.cutsTheMustard = typeof features !== 'undefined' && features.rAF && features.bind && features.classList;

    window.Headroom = Headroom;


}(window, document));

/*!
 * headroom.js v0.7.0 - Give your page some headroom. Hide your header until you need it
* Copyright (c) 2014 Nick Williams - http://wicky.nillia.ms/headroom.js
* License: MIT
*/

(function($) {

 if(!$) {
     return;
 }

 ////////////
 // Plugin //
 ////////////

 $.fn.headroom = function(option) {
     return this.each(function() {
         var $this   = $(this),
             data      = $this.data('headroom'),
             options   = typeof option === 'object' && option;

         options = $.extend(true, {}, Headroom.options, options);

         if (!data) {
             data = new Headroom(this, options);
             data.init();
             $this.data('headroom', data);
         }
         if (typeof option === 'string') {
             data[option]();
         }
     });
 };

 //////////////
 // Data API //
 //////////////

 $('[data-headroom]').each(function() {
     var $this = $(this);
     $this.headroom($this.data());
 });

}(window.Zepto || window.jQuery));


(function ($) {
    "use strict";

    var $devicewidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
    var $deviceheight = (window.innerHeight > 0) ? window.innerHeight : screen.height;
    var $bodyel = jQuery("body");
    var $navbarel = jQuery(".navbar");

    var $lgWidth = 1199;
    var $mdWidth = 991;
    var $smWidth = 767;
    var $xsWidth = 479;

    /* ========================== */
    /* ==== HELPER FUNCTIONS ==== */

    function validatedata($attr, $defaultValue) {
        "use strict";
        if ($attr !== undefined) {
            return $attr
        }
        return $defaultValue;
    }

    function parseBoolean(str, $defaultValue) {
        "use strict";
        if (str == 'true') {
            return true;
        } else if (str == "false") {
            return false;
        }
        return $defaultValue;
    }
    if(document.getElementById('ct-js-wrapper')){
        snapper = new Snap({
            element: document.getElementById('ct-js-wrapper')
        });

        snapper.settings({
            easing: 'ease',
            addBodyClasses: true,
            slideIntent: 20
        });
    }
    //Extended search input in navbar // ----------------------------------------

    $(document).mouseup(function (e) {
        var $searchform = $(".ct-navbar-search");

        if(!$('#ct-js-navSearch').is(e.target)){
            if (!$searchform.is(e.target) // if the target of the click isn't the container...
                && $searchform.has(e.target).length === 0) // ... nor a descendant of the container
            {
                $navbarel.removeClass('is-inactive');
                //$(".ct-navbar-search form").append("<i class='fa fa-times ct-navbar-search-closeIcon'></i>");
                $searchform.fadeOut();
            }
        }
    });
    $(window).on('resize', function() {
        if ($(window).width() < 768) {
            snapper.enable();
        } else{
            snapper.disable();
        }
    });


    $(window).scroll(function() {
        if ($(window).scrollTop() == 0) {
            $(".ct-topBar--transparent").css("position", "fixed").css("top", "0");
            $(".navbar-transparent .navbar-header img").attr("src", "assets/images/content/logo-light.png")
        } else{
            $(".ct-topBar--transparent").css("position", "absolute").css("top", "-40px");
            $(".navbar-transparent .navbar-header img").attr("src", $navbarel.attr("data-changeLogo"))
        }
    });
    $(window).on("load", function(){
        var $preloader = $('.ct-preloader');
        var $content = $('.ct-preloader-content');

        var $timeout = setTimeout(function(){
            $($preloader).addClass('animated').addClass('fadeOut');
            $($content).addClass('animated').addClass('fadeOut');
        }, 0);
        var $timeout2 = setTimeout(function(){
            $($preloader).css('display', 'none').css('z-index', '-9999');
        }, 500);
        if (!device.mobile() && !device.tablet()) {
            /*$(window).stellar({
                horizontalScrolling   : false,
                verticalScrolling     : true,
                responsive            : true,
                horizontalOffset      : 0,
                verticalOffset        : 0,
                parallaxBackgrounds   : true,
                parallaxElements      : true,
                scrollProperty        : 'scroll',
                positionProperty      : 'position',
                hideDistantElements   : true
            });*/
        }

        if ($().appear) {
            if (device.mobile() || device.tablet()) {
                $bodyel.removeClass("cssAnimate");
            } else {
                // Skrollr Refresh// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

                setTimeout(function () {
                    $(window).trigger('resize');
                    skrollr.get().refresh();
                }, 100);
            }
        }

    });
    $(window).trigger('resize');
})(jQuery);
/*By Lucas Fajger

What we needa here!
---------------------------
1.CSS

 Navbar scroll top class u should style on your own how width are your fixed elements, if they are not witdh from top: 0, left: 0 then we remove left:0;
 .navbar-scroll-top {
 position: fixed;
 left: 0;
 top: 0;
 width: 100%;
 z-index: 9999;
 }

 //headroom styles for fixed headers and menus
 .headroom {
 transition: transform 200ms linear;
 z-index: 9998;
 }
 .headroom--pinned {
 transform: translateY(0%);
 display: block;
 }
 .headroom--unpinned {
 transform: translateY(-100%);
 display: none;
 }

 .animatedHeadroom {
 -webkit-animation-duration: 1s;
 animation-duration: 1s;
 -webkit-animation-fill-mode: both;
 animation-fill-mode: both;
 }

 .animatedHeadroom.infinite {
 -webkit-animation-iteration-count: infinite;
 animation-iteration-count: infinite;
 }

 .animatedHeadroom.hinge {
 -webkit-animation-duration: 2s;
 animation-duration: 2s;
 }

2. CLASSES inside tags - required
 For topBar - ct-topBar  <div class="ct-topBar">
 For menu - navbar <nav class="navbar"> - no matter if it's nav or div :)

2.1 Classes in Body we use for different variations

 Body classes Navbars/TopBars:

 ct-headroom--scrollUpMenu
 ct-headroom--scrollUpTopBar
 ct-headroom--scrollUpBoth
 ct-headroom--fixedTopBar
 ct-headroom--fixedMenu
 ct-headroom--fixedBoth
 ct-headroom--hideMenu

 <body class="ct-headroom--fixedBoth">


3. if you dont have in your main.js $topbarel and $navbarel and $bodyel variables then copy them at the beggining in this function:

 var $bodyel = jQuery("body");
 var $navbarel = jQuery(".navbar");
 var $topbarel = jQuery(".ct-topBar");


4. if you dont have the function validate in main js, copy this before this init

 function validatedata($attr, $defaultValue) {
 "use strict";
 if ($attr !== undefined) {
 return $attr
 }
 return $defaultValue;
 }

TIP: If you have both elements with this effect deployed in main html then good practise is set the same offset for the both elements
TIP: We have default values as data attributes, but if you mind you can change it on your own:
EXAMPLE: <div class="ct-topBar" data-starttopbar=300 data-offset="500">

 */

(function($){
    "use strict";
    var $bodyel = jQuery("body");
    var $navbarel = jQuery(".navbar");
    var $topbarel = jQuery(".ct-topBar");
    function validatedata($attr, $defaultValue) {
        "use strict";
        if ($attr !== undefined) {
            return $attr
        }
        return $defaultValue;
    }

    $(document).ready(function () {
        var $headroomStr = "ct-js-headroom";
        var $headroomCla = ".ct-js-headroom";
        var $topBarStr = "ct-topBar";
        var $navBarStr = "navbar";

        if($bodyel.hasClass("ct-headroom--scrollUpMenu")){
            $navbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--scrollUpTopBar")){
            $topbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--scrollUpBoth")){
            var $scrollUpBoth = true;
            $topbarel.addClass($headroomStr);
            $navbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--fixedTopBar")){
            var $fixedTopBar = true;
            $topbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--fixedMenu")){
            var $fixedMenu = true;
            $navbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--fixedBoth")){
            var $fixedBoth = true;
            var $scrollUpBoth = true;
            $topbarel.addClass($headroomStr);
            $navbarel.addClass($headroomStr);
        }
        else if($bodyel.hasClass("ct-headroom--hideMenu")){
            var $fixedScrollUpTopBar = true;
            var $scrollUpTopBar = true;
            $topbarel.addClass($headroomStr);
            $navbarel.addClass($headroomStr);
        }
        else{
            return;
        }

        if($($headroomCla).length > 0){
            $($headroomCla).each(function(){
                var $this = $(this);

                //Position of the topBar and navbar, when (scroll position) we grab it
                var $startPositionTopBar = 0;
                var $startPositionNavbar = 118;

                var ctstarttopbar = validatedata($this.attr("data-starttopbar"), $startPositionTopBar); //default position 0
                var ctstartnavbar = validatedata($this.attr("data-startnavbar"), $startPositionNavbar); //default position 170

                $(window).scroll(function(){
                    var scrollPos = $(window).scrollTop();

                    if ($this.hasClass($topBarStr)){
                        if (scrollPos > ctstarttopbar){
                            $this.addClass("navbar-scroll-top");
                        }
                        else{
                            $this.removeClass("navbar-scroll-top");
                        }
                    }
                    else if($this.hasClass($navBarStr)){
                        if (scrollPos >  ctstartnavbar){
                            $this.addClass("navbar-scroll-top");

                            if($scrollUpBoth || $scrollUpTopBar){
                                //this attribute we put in navbar only if we use ct-headroom--scrollUpBoth, ct-headroom--fixedBoth, ct-headroom--hideMenu
                                var ctheighttopbar = validatedata($this.attr("data-heighttopbar"), "50px"); // height of topbar needed for positiong menu below topbar exact how height is topbar :)
                                $this.css("top",ctheighttopbar); //add 50px for menu coz topbar has 50px, we want to put it below
                            }
                        }
                        else{
                            $this.removeClass("navbar-scroll-top");
                            if($scrollUpBoth || $scrollUpTopBar){
                                $this.css("top","auto");
                            }
                        }
                    }
                });

                var ctoffset = validatedata($this.attr("data-offset"), 205); //this is the offset when taken elements have to disappear

                var cttolerance = validatedata($this.attr("data-tolerance"), 5); /// you can specify tolerance individually for up/down scroll
                var ctinitiial = validatedata($this.attr("data-initial"), "animatedHeadroom"); // when element is initialised
                var cttop = validatedata($this.attr("data-top"), "headroom--top");  // when above offset
                var ctnotTop = validatedata($this.attr("data-top"), "headroom--not-top"); // when below offset

                if($fixedScrollUpTopBar){
                    if($this.hasClass("ct-topBar")){
                        var $fixedScrollUpTopBarConfirmed = true;
                    }
                }

                if($fixedBoth || $fixedTopBar || $fixedMenu || $fixedScrollUpTopBarConfirmed){
                    //if you want to fix elements for good, then we should change variables so that they are with the same name, no matter what
                    var ctpinned = validatedata($this.attr("data-pinned"), "IAmFixed");
                    var ctunpinned = validatedata($this.attr("data-unpinned"), "IAmFixed");
                }
                else{
                    var ctpinned = validatedata($this.attr("data-pinned"), "fadeInDown"); //effect when elements appears itself -  when scrolling up
                    var ctunpinned = validatedata($this.attr("data-unpinned"), "fadeOutUp"); //effect when elements disappears itself -  when scrolling down
                }

                $this.headroom({ //do this for each element use  add .ct-js-headroom

                    "offset": ctoffset,// vertical offset in px before element is first unpinned
                    "tolerance": cttolerance, // scroll tolerance in px before state changes
                    "top": cttop, // when above offset
                    "notTop": ctnotTop, // when below offset

                    "classes": {
                        "initial": ctinitiial, // when element is initialised
                        "pinned": ctpinned, // when scrolling up
                        "unpinned": ctunpinned // when scrolling down
                    }
                });
            });
        }
    });
})(jQuery);
$(document).ready(function () {
	var $bodyel = jQuery("body");
	var $navbarel = jQuery(".navbar");
	 
    $(".navbar .navbar-nav > li").on("mouseenter", function(){
        $(this).addClass("ct-active").siblings().removeClass('ct-active');
    });

    $(".navbar .navbar-nav > li >a").on("mouseenter", function(){
        $(".navbar .navbar-nav").addClass('ct-navbar--fadeInUp');
    });
    $(".navbar .navbar-nav > li >a").on("mouseleave", function(){
        $(".navbar .navbar-nav").removeClass('ct-navbar--fadeInUp');
    });

    $(".navbar .navbar-nav > li .dropdown-menu").on("mouseleave", function(){
        $(".navbar .navbar-nav > li").removeClass('ct-active');
    });

    $(".navbar, .ct-topBar").wrapAll("<div class='navbar-wrapper'></div>");
    if($navbarel.hasClass("navbar-parts")){
        $navbarel.parent().addClass("navbar-wrapperBig");
    }

    /*$(".ct-mediaSection").mediaSection();*/

    //Position IMG

    if ($devicewidth >= 1200 && document.getElementById('ct-js-wrapper')) {
        $(".ct-js-imageOffset").each(function(){
            $(this).css("position", "absolute");
            $(this).css("top", $(this).attr("data-top")+'px');
            $(this).css("bottom", $(this).attr("data-bottom")+'px');
            $(this).css("left", $(this).attr("data-left")+'px');
            $(this).css("right", $(this).attr("data-right")+'px');
        });
    }

    // Add Color // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    $(".ct-js-color").each(function(){
        $(this).css("color", '#' + $(this).attr("data-color"))
    });

    // Navbar Search // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    var $searchform = $(".ct-navbar-search");
    $('#ct-js-navSearch').on("click", function(e){
        e.preventDefault();
        $navbarel.addClass('is-inactive');


        $searchform.fadeIn();

        if (($searchform).is(":visible")) {
            $searchform.find("[type=text]").focus();
        }

        return false;
    })

    // Snap Navigation in Mobile // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    if ($devicewidth > 767 && document.getElementById('ct-js-wrapper')) {
        snapper.disable();
    }

    $(".navbar-toggle").click(function () {
        if($bodyel.hasClass('snapjs-left')){
            snapper.close();
        } else{
            snapper.open('left');
        }
    });

    $(".ct-navbarCart--mobileIcon").on("click", function () {
        if($bodyel.hasClass('snapjs-right')){
            snapper.close();
        } else{
            snapper.open('right');
        }
    });

    $('.ct-js-slick').attr('data-snap-ignore', 'true'); // Ignore Slick

    $('.ct-menuMobile .ct-menuMobile-navbar .dropdown > a').click(function(e) {
        return false; // iOS SUCKS
    });
    $('.ct-menuMobile .ct-menuMobile-navbar .dropdown > a').click(function(e){
        var $this = $(this);
        if($this.parent().hasClass('open')){
            $(this).parent().removeClass('open');
        } else{
            $('.ct-menuMobile .ct-menuMobile-navbar .dropdown.open').toggleClass('open');
            $(this).parent().addClass('open');
        }
    });

    $('.ct-progressPath .dropdown > a').click(function(e) {
        return false; // iOS SUCKS
    });
    $('.ct-progressPath .dropdown > a').click(function(e){
        var $this = $(this);
        if($this.parent().hasClass('open')){
            $(this).parent().removeClass('open');
        } else{
            $('.ct-progressPath .dropdown.open').toggleClass('open');
            $(this).parent().addClass('open');
        }
    });

    $('.ct-menuMobile .ct-menuMobile-navbar .onepage > a').click(function(e) {
        snapper.close();
    });
    // Tooltips and Popovers // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    $("[data-toggle='tooltip']").tooltip();

    $("[data-toggle='popover']").popover({trigger: "hover", html: true});


    // Link Scroll to Section // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    function goToByScroll(id) {
        $('html,body').animate({scrollTop: $(id).offset().top - 70}, 'slow');
    }

    $('body .ct-js-btnScroll').click(function () {
        goToByScroll($(this).attr('href'));
        return false;
    });

    $('.ct-js-btnScrollUp').click(function (e) {
        e.preventDefault();
        $("body,html").animate({scrollTop: 0}, 1200);
        console.log($navbarel);
        $navbarel.find('.onepage').removeClass('active');
        $navbarel.find('.onepage:first-child').addClass('active');
        return false;
    });

    // Placeholder Fallback // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    if ($().placeholder) {
        $("input[placeholder],textarea[placeholder]").placeholder();
    }

    // Animations Init // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    if ($().appear) {
        if (device.mobile() || device.tablet()) {
            $bodyel.removeClass("cssAnimate");
        } else {

            $('.cssAnimate .animated').appear(function () {
                var $this = $(this);

                $this.each(function () {
                    if ($this.data('time') != undefined) {
                        setTimeout(function () {
                            $this.addClass('activate');
                            $this.addClass($this.data('fx'));
                        }, $this.data('time'));
                    } else {
                        $this.addClass('activate');
                        $this.addClass($this.data('fx'));
                    }
                });
            }, {accX: 50, accY: -350});

            // Skrollr // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            var s = skrollr.init({
                forceHeight: false
            });
        }
    }
    $(".ct-navbar-search-closeIcon").on("click", function(){
        $navbarel.removeClass('is-inactive');
        $searchform.fadeOut();
    })

    //Search

    $(".ct-searchCourse").each(function(){
        $(this).on("keydown",function search(e) {
            if(e.keyCode == 13) {
                $('.ct-js-searchModal').each(function(){
                    $(this).modal({
                        show: true
                    })
                });
                var searchArray = [];
                var inputValue = $(this).val();
                $('.ct-js-searchModal .modal-body *').remove();
                $.ajax({
                    url: 'assets/json/search.json',
                    success: function(data) {
                        for(var i = 0; data.length > i; i++ ){
                            var arrayCourseName = data[i].courseName;

                            if(arrayCourseName.toLowerCase().indexOf(inputValue.toLowerCase()) != -1){
                                searchArray.push(data[i]);
                                $('.ct-js-searchModal .modal-body').append(
                                    "<div class='ct-productBox ct-productBox--inline ct-u-displayTable ct-u-marginBottom30'>"+
                                        "<div class='ct-u-displayTableCell'>"+
                                            "<div class='ct-productImage'>"+
                                                "<a href='course-single.html'>"+
                                                    "<img src='"+data[i].thumbnailImage+"' alt='Product'>"+
                                                "</a>"+
                                            "</div>"+
                                        "</div>"+
                                        "<div class='ct-u-displayTableCell'>"+
                                            "<div class='ct-productDescription'>"+
                                                "<a href='course-single.html'>"+
                                                    "<h5 class='ct-fw-600 ct-u-marginBottom10'>"+data[i].courseName+"</h5>"+
                                                "</a>"+
                                                "<span>"+data[i].courseDescription+"</span>"+
                                            "</div>"+
                                            "<div class='ct-productMeta'>"+
                                                "<div class='ct-u-displayTableVertical'>"+
                                                    "<div class='ct-u-displayTableCell'>"+
                                                        "<div class='starrr' data-rating='"+data[i].stars+"'></div>"+
                                                    "</div>"+
                                                    "<div class='ct-u-displayTableCell'>"+
                                                        "<span class='ct-u-colorMotive'>"+data[i].price+"</span>"+
                                                        "<span class='ct-u-textLineThrough'>290$</span>"+
                                                    "</div>"+
                                                    "<div class='ct-u-displayTableCell'>"+
                                                        "<a href='#'>"+
                                                        +data[i].commentsCourse+" <i class='fa fa-user'></i>"+
                                                        "</a>"+
                                                    "</div>"+
                                            "</div>"+
                                        "</div>"+
                                    "</div>"
                                )
                            }
                        }
                        if(searchArray.length == 0){
                            $(".ct-js-searchModal .modal-header").hide();
                            $('.ct-js-searchModal .modal-body *').remove();
                            $('.ct-js-searchModal .modal-body').append("<h2 class='ct-u-paddingBoth100 text-center'>No results found</h2>")
                        }else{
                            $(".ct-js-searchModal .modal-header").show();
                            $('.ct-js-searchModal .modal-title').text("We found "+searchArray.length+" courses.")
                        }
                        $(".ct-js-searchModal .modal-body .starrr").starrr({
                            noSet: true
                        });
                    }
                });
            }
        });
    });

    //Triger keydown event on search button
    var e = jQuery.Event("keydown");
    e.which = 13;
    e.keyCode = 13;
    $(".ct-search-button, .ct-navbar-search-button").on("click", function(){
        $(".ct-searchCourse").trigger(e)
    })

    // Log in & Sign up modal
  /*  $('.ct-js-login').on("click", function (event) {
    	//event.preventDefault();
    	
        $(".ct-js-modal-login").modal({
            show: true
        })
        
    });

    $('.ct-js-signup').on("click", function () {
        $(".ct-js-modal-signup").modal({
            show: true
        })
    });*/

});