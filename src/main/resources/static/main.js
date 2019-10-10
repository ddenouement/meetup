(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./$$_lazy_route_resource lazy recursive":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html":
/*!**************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html ***!
  \**************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<app-toolbar-menu></app-toolbar-menu>\r\n<router-outlet></router-outlet>\r\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/home/home.component.html":
/*!********************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/home/home.component.html ***!
  \********************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div class=\"container\">\r\n  <img src=\"../../assets/images/meetup1.jpg\" alt=\"Meetup\">\r\n  <button class=\"btn\">Join meetup</button>\r\n</div>\r\n<div class=\"topics\">\r\n  <mat-grid-list cols=\"4\" rowHeight=\"1:1\">\r\n    <mat-grid-tile>\r\n      <mat-card class=\"topic-card\">\r\n        <img mat-card-image src=\"../../assets/images/shopping.JPG\" alt=\"Shopping\">\r\n        <mat-card-title>Shopping</mat-card-title>\r\n      </mat-card>\r\n    </mat-grid-tile>\r\n    <mat-grid-tile>\r\n      <mat-card class=\"topic-card\">\r\n        <img mat-card-image src=\"../../assets/images/games.JPG\" alt=\"Games\">\r\n        <mat-card-title>Games</mat-card-title>\r\n      </mat-card>\r\n    </mat-grid-tile>\r\n    <mat-grid-tile>\r\n      <mat-card class=\"topic-card\">\r\n        <img mat-card-image src=\"../../assets/images/music.JPG\" alt=\"Music\">\r\n        <mat-card-title>Music</mat-card-title>\r\n      </mat-card>\r\n    </mat-grid-tile>\r\n    <mat-grid-tile>\r\n      <mat-card class=\"topic-card\">\r\n        <img mat-card-image src=\"../../assets/images/travel.JPG\" alt=\"Travel\">\r\n        <mat-card-title>Travel</mat-card-title>\r\n      </mat-card>\r\n    </mat-grid-tile>\r\n  </mat-grid-list>\r\n\r\n</div>\r\n\r\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/login/login.component.html":
/*!**********************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/login/login.component.html ***!
  \**********************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div>\r\n  <main>\r\n    <div class=\"content\">\r\n      <form [formGroup]=\"loginForm\" (ngSubmit)=\"onSubmit()\">\r\n        <p class=\"form__headline\">Log In</p>\r\n        <input class=\"form__input\" matInput formControlName=\"login\" type=\"text\" placeholder=\"Login\"/>\r\n        <input class=\"form__input\" matInput formControlName=\"password\" type=\"password\" placeholder=\"Password\"/>\r\n        <a href='reset.html' class=\"form__help\">Forgot password?</a>\r\n        <button class=\"form__button\" [disabled]=\"!loginForm.valid\" disabled>Sign In</button>\r\n      </form>\r\n    </div>\r\n  </main>\r\n</div>\r\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/register-speaker/register-speaker.component.html":
/*!********************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/register-speaker/register-speaker.component.html ***!
  \********************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div>\r\n  <main>\r\n    <div class=\"content\">\r\n      <form [formGroup]=\"registerForm\" (ngSubmit)=\"onSubmit()\">\r\n        <p class=\"form__headline\">Sign up as speaker</p>\r\n        <input class=\"form__input\" matInput formControlName=\"login\" type=\"text\" placeholder=\"Login\"/>\r\n        <input class=\"form__input\" matInput formControlName=\"email\" type=\"email\" placeholder=\"E-mail\"/>\r\n        <input type=\"password\" class=\"form__input password\" matInput formControlName=\"password\" placeholder=\"Create password\">\r\n        <div class=\"complexity\">\r\n          <div class=\"complexity__item\"></div>\r\n          <div class=\"complexity__item\"></div>\r\n          <div class=\"complexity__item\"></div>\r\n        </div>\r\n        <input type=\"password\" class=\"form__input\" matInput formControlName=\"confirmPassword\" placeholder=\"Confirm password\">\r\n        <button class=\"form__button\" type=\"submit\" [disabled]=\"!registerForm.valid\" disabled>Sign Up</button>\r\n      </form>\r\n    </div>\r\n  </main>\r\n</div>\r\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/register/register.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/register/register.component.html ***!
  \****************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<div>\r\n  <main>\r\n    <div class=\"content\">\r\n      <form [formGroup]=\"registerForm\" (ngSubmit)=\"onSubmit()\">\r\n        <p class=\"form__headline\">Sign up as listener</p>\r\n        <div class=\"form__user\">\r\n        <input type=\"text\" class=\"form__input\" matInput formControlName=\"firstName\" placeholder=\"First name\"/>\r\n        <input type=\"text\" class=\"form__input\" matInput formControlName=\"lastName\" placeholder=\"Last name\"/>\r\n        </div>\r\n        <input class=\"form__input\" matInput formControlName=\"login\" type=\"text\" placeholder=\"Login\" />\r\n        <input class=\"form__input\" matInput formControlName=\"email\" type=\"email\" placeholder=\"E-mail\" />\r\n        <input type=\"password\" class=\"form__input password\" matInput formControlName=\"password\" placeholder=\"Create password\" >\r\n        <div class=\"complexity\">\r\n          <div class=\"complexity__item\"></div>\r\n          <div class=\"complexity__item\"></div>\r\n          <div class=\"complexity__item\"></div>\r\n        </div>\r\n        <input type=\"password\" class=\"form__input\" matInput formControlName=\"confirmPassword\"  placeholder=\"Confirm password\">\r\n        <button class=\"form__button\" type=\"submit\" [disabled]=\"!registerForm.valid\" disabled>Sign Up</button>\r\n      </form>\r\n    </div>\r\n  </main>\r\n</div>\r\n");

/***/ }),

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/toolbar-menu/toolbar-menu.component.html":
/*!************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/toolbar-menu/toolbar-menu.component.html ***!
  \************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<mat-toolbar>\r\n  <a href=\"#\" style=\"display: flex; flex-direction: row; align-items: center; outline:none;\" routerLink=\"/\">\r\n  <svg style=\"margin-right: 4px\" data-color=\"1\" dc=\"http://purl.org/dc/elements/1.1/\" cc=\"http://creativecommons.org/ns#\"\r\n       rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" svg=\"http://www.w3.org/2000/svg\"\r\n       xmlns=\"http://www.w3.org/2000/svg\" sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n       inkscape=\"http://www.inkscape.org/namespaces/inkscape\" viewBox=\"2 4 60 56\" version=\"1.1\" x=\"0px\" y=\"0px\"\r\n       width=\"60\" height=\"56\" fill=\"#322b6b\">\r\n    <g transform=\"translate(0,-988.36219)\">\r\n      <path style=\"\"\r\n            d=\"M 28 4 L 28 30 L 36 30 L 36 4 L 28 4 z M 16 12 L 16 30 L 24 30 L 24 12 L 16 12 z M 52 14 L 52 30 L 60 30 L 60 14 L 52 14 z M 4 19 L 4 30 L 12 30 L 12 19 L 4 19 z M 40 21 L 40 30 L 48 30 L 48 21 L 40 21 z M 3.0351562 31 C 2.4620529 31 2 31.431297 2 31.966797 L 2 32.033203 C 2 32.568703 2.4620529 32.9997 3.0351562 33 L 4 33 L 4 33.814453 L 4.8144531 33 L 6.2285156 33 L 4 35.228516 L 4 36.642578 L 7.6425781 33 L 9.0566406 33 L 4 38.056641 L 4 39.470703 L 10.470703 33 L 11.884766 33 L 4 40.884766 L 4 42.298828 L 12 34.298828 L 12 33 L 16 33 L 16 34.119141 L 17.119141 33 L 18.533203 33 L 16 35.533203 L 16 36.947266 L 19.947266 33 L 21.361328 33 L 16.232422 38.130859 L 16 38.363281 L 16 39.777344 L 16.939453 38.837891 L 22.775391 33 L 28 33 L 28 34.119141 L 29.119141 33 L 30.533203 33 L 28 35.533203 L 28 36.947266 L 31.947266 33 L 33.361328 33 L 28.232422 38.130859 L 28 38.363281 L 28 39.777344 L 28.939453 38.837891 L 34.775391 33 L 40 33 L 40 34.119141 L 41.119141 33 L 42.533203 33 L 40 35.533203 L 40 36.947266 L 43.947266 33 L 45.361328 33 L 40.232422 38.130859 L 40 38.363281 L 40 39.777344 L 40.939453 38.837891 L 46.775391 33 L 52 33 L 52 34.119141 L 53.119141 33 L 54.533203 33 L 52 35.533203 L 52 36.947266 L 55.947266 33 L 57.361328 33 L 52.232422 38.130859 L 52 38.363281 L 52 39.777344 L 52.939453 38.837891 L 58.775391 33 L 60.964844 33 C 61.537948 33 62 32.568703 62 32.033203 L 62 31.966797 C 62 31.431297 61.537948 31 60.964844 31 L 3.0351562 31 z M 24 33.191406 L 17.646484 39.544922 L 16.232422 40.958984 L 16 41.191406 L 16 42.605469 L 16.939453 41.666016 L 18.353516 40.251953 L 24 34.605469 L 24 33.191406 z M 36 33.191406 L 29.646484 39.544922 L 28.232422 40.958984 L 28 41.191406 L 28 42.605469 L 28.939453 41.666016 L 30.353516 40.251953 L 36 34.605469 L 36 33.191406 z M 48 33.191406 L 41.646484 39.544922 L 40.232422 40.958984 L 40 41.191406 L 40 42.605469 L 40.939453 41.666016 L 42.353516 40.251953 L 48 34.605469 L 48 33.191406 z M 60 33.191406 L 53.646484 39.544922 L 52.232422 40.958984 L 52 41.191406 L 52 42.605469 L 52.939453 41.666016 L 54.353516 40.251953 L 60 34.605469 L 60 33.191406 z M 12 35.712891 L 4 43.712891 L 4 45 L 4.1855469 45 L 4.15625 44.970703 L 12 37.126953 L 12 35.712891 z M 24 36.019531 L 19.060547 40.958984 L 17.646484 42.373047 L 16 44.019531 L 16 45.433594 L 19.767578 41.666016 L 24 37.433594 L 24 36.019531 z M 36 36.019531 L 31.060547 40.958984 L 29.646484 42.373047 L 28 44.019531 L 28 45.433594 L 31.767578 41.666016 L 36 37.433594 L 36 36.019531 z M 48 36.019531 L 43.060547 40.958984 L 41.646484 42.373047 L 41.019531 43 L 42.433594 43 L 43.767578 41.666016 L 48 37.433594 L 48 36.019531 z M 60 36.019531 L 55.060547 40.958984 L 53.646484 42.373047 L 52 44.019531 L 52 45.433594 L 55.767578 41.666016 L 60 37.433594 L 60 36.019531 z M 12 38.541016 L 5.5410156 45 L 6.9550781 45 L 12 39.955078 L 12 38.541016 z M 24 38.847656 L 20.474609 42.373047 L 16 46.847656 L 16 48.261719 L 21.181641 43.080078 L 24 40.261719 L 24 38.847656 z M 36 38.847656 L 32.474609 42.373047 L 28 46.847656 L 28 48.261719 L 33.181641 43.080078 L 36 40.261719 L 36 38.847656 z M 48 38.847656 L 44.474609 42.373047 L 43.847656 43 L 45.261719 43 L 48 40.261719 L 48 38.847656 z M 60 38.847656 L 56.474609 42.373047 L 52 46.847656 L 52 48.261719 L 57.181641 43.080078 L 60 40.261719 L 60 38.847656 z M 12 41.369141 L 8.3691406 45 L 9.7832031 45 L 12 42.783203 L 12 41.369141 z M 24 41.675781 L 21.888672 43.787109 L 16 49.675781 L 16 51.091797 L 22.595703 44.494141 L 24 43.089844 L 24 41.675781 z M 36 41.675781 L 33.888672 43.787109 L 28 49.675781 L 28 51.091797 L 34.595703 44.494141 L 36 43.089844 L 36 41.675781 z M 48 41.675781 L 46.675781 43 L 48 43 L 48 41.675781 z M 60 41.675781 L 57.888672 43.787109 L 52 49.675781 L 52 50 L 53.091797 50 L 58.595703 44.494141 L 60 43.089844 L 60 41.675781 z M 12 44.199219 L 11.199219 45 L 12 45 L 12 44.199219 z M 24 44.503906 L 23.302734 45.201172 L 16.505859 52 L 17.919922 52 L 24 45.917969 L 24 44.503906 z M 36 44.503906 L 35.302734 45.201172 L 28 52.505859 L 28 53.919922 L 36 45.917969 L 36 44.503906 z M 60 44.503906 L 59.302734 45.201172 L 54.505859 50 L 55.919922 50 L 60 45.917969 L 60 44.503906 z M 24 47.332031 L 19.333984 52 L 20.746094 52 L 24 48.746094 L 24 47.332031 z M 36 47.332031 L 28 55.333984 L 28 56.748047 L 36 48.746094 L 36 47.332031 z M 60 47.332031 L 57.332031 50 L 58.746094 50 L 60 48.746094 L 60 47.332031 z M 24 50.160156 L 22.160156 52 L 23.574219 52 L 24 51.574219 L 24 50.160156 z M 36 50.160156 L 28 58.162109 L 28 59.576172 L 36 51.574219 L 36 50.160156 z M 36 52.990234 L 28.990234 60 L 30.404297 60 L 36 54.404297 L 36 52.990234 z M 36 55.818359 L 31.818359 60 L 33.232422 60 L 36 57.232422 L 36 55.818359 z M 36 58.646484 L 34.646484 60 L 36 60 L 36 58.646484 z \"\r\n            transform=\"translate(0,988.36219)\"></path>\r\n    </g>\r\n  </svg>\r\n  <svg xmlns=\"http://www.w3.org/2000/svg\" xlink=\"http://www.w3.org/1999/xlink\" width=\"89.65249633789062\"\r\n       viewBox=\"1.627500057220459 8.609999656677246 89.65249633789062 32.72999954223633\" overflow=\"visible\"\r\n       style=\"overflow: visible;\">\r\n    <path stroke=\"none\" fill=\"#322b6b\"\r\n          d=\"M20.85 35.30L20.85 35.30L20.85 35.30Q19.79 35.26 19.41 34.65L19.41 34.65L19.41 34.65Q19.02 34.03 18.95 32.94L18.95 32.94L18.95 32.94Q18.88 31.85 19.02 30.36L19.02 30.36L19.02 30.36Q19.16 28.86 19.16 27.11L19.16 27.11L19.16 27.11Q19.20 23.63 19.42 20.76L19.42 20.76L19.42 20.76Q19.65 17.89 19.55 15.19L19.55 15.19L19.55 15.19Q19.55 14.98 19.55 14.80L19.55 14.80L19.55 14.80Q19.55 14.63 19.51 14.45L19.51 14.45L19.51 14.45Q19.34 15.05 19.11 15.80L19.11 15.80L19.11 15.80Q18.88 16.56 18.56 17.47L18.56 17.47L18.56 17.47Q17.93 19.44 17.31 21.52L17.31 21.52L17.31 21.52Q16.70 23.59 16.12 25.54L16.12 25.54L16.12 25.54Q15.54 27.49 15.03 29.16L15.03 29.16L15.03 29.16Q14.52 30.83 14.13 31.96L14.13 31.96L14.13 31.96Q13.75 33.08 13.38 33.63L13.38 33.63L13.38 33.63Q13.01 34.17 12.64 34.38L12.64 34.38L12.64 34.38Q12.27 34.59 11.90 34.56L11.90 34.56L11.90 34.56Q11.53 34.52 11.18 34.49L11.18 34.49L11.18 34.49Q11.04 33.29 10.65 31.68L10.65 31.68L10.65 31.68Q10.27 30.06 9.84 28.58L9.84 28.58L9.84 28.58Q9.35 26.86 8.75 25.07L8.75 25.07L8.75 25.07Q7.95 21.97 7.07 19.41L7.07 19.41L7.07 19.41Q6.19 16.84 5.70 15.19L5.70 15.19L5.70 15.19Q5.48 16.98 5.38 19.30L5.38 19.30L5.38 19.30Q5.27 21.62 5.24 23.94L5.24 23.94L5.24 23.94Q5.20 26.26 5.27 28.32L5.27 28.32L5.27 28.32Q5.34 30.38 5.48 31.64L5.48 31.64L5.48 31.64Q5.52 32.17 5.63 32.70L5.63 32.70L5.63 32.70Q5.70 33.15 5.82 33.63L5.82 33.63L5.82 33.63Q5.94 34.10 6.12 34.49L6.12 34.49L6.12 34.49Q5.94 34.63 5.70 34.73L5.70 34.73L5.70 34.73Q5.45 34.84 5.17 34.91L5.17 34.91L5.17 34.91Q4.85 35.02 4.50 35.09L4.50 35.09L4.50 35.09Q3.13 35.37 2.51 35.02L2.51 35.02L2.51 35.02Q1.90 34.66 1.76 33.61L1.76 33.61L1.76 33.61Q1.69 33.08 1.65 31.64L1.65 31.64L1.65 31.64Q1.62 30.20 1.63 28.32L1.63 28.32L1.63 28.32Q1.65 26.44 1.72 24.36L1.72 24.36L1.72 24.36Q1.79 22.29 1.93 20.50L1.93 20.50L1.93 20.50Q2.07 19.05 2.21 17.37L2.21 17.37L2.21 17.37Q2.36 15.89 2.50 13.96L2.50 13.96L2.50 13.96Q2.64 12.02 2.81 9.77L2.81 9.77L2.81 9.77Q2.92 9.56 3.16 9.40L3.16 9.40L3.16 9.40Q3.41 9.25 3.69 9.14L3.69 9.14L3.69 9.14Q4.01 9 4.39 8.93L4.39 8.93L4.39 8.93Q4.99 8.82 5.45 8.75L5.45 8.75L5.45 8.75Q5.84 8.68 6.17 8.65L6.17 8.65L6.17 8.65Q6.50 8.61 6.43 8.65L6.43 8.65L6.43 8.65Q6.89 9.60 7.14 10.21L7.14 10.21L7.14 10.21Q7.38 10.83 7.65 11.67L7.65 11.67L7.65 11.67Q7.91 12.52 8.31 13.89L8.31 13.89L8.31 13.89Q8.72 15.26 9.49 17.72L9.49 17.72L9.49 17.72Q10.55 21.13 11.30 23.66L11.30 23.66L11.30 23.66Q12.06 26.19 12.45 27.53L12.45 27.53L12.45 27.53Q12.69 27.07 12.94 26.53L12.94 26.53L12.94 26.53Q13.18 25.98 13.45 25.21L13.45 25.21L13.45 25.21Q13.71 24.43 14.01 23.40L14.01 23.40L14.01 23.40Q14.31 22.36 14.70 20.92L14.70 20.92L14.70 20.92Q15.19 19.34 15.71 17.51L15.71 17.51L15.71 17.51Q16.14 15.96 16.65 13.97L16.65 13.97L16.65 13.97Q17.16 11.99 17.61 9.84L17.61 9.84L17.61 9.84Q18.74 9.39 19.44 9.16L19.44 9.16L19.44 9.16Q20.14 8.93 20.53 8.79L20.53 8.79L20.53 8.79Q20.99 8.65 21.20 8.61L21.20 8.61L21.20 8.61Q21.41 8.65 21.62 8.82L21.62 8.82L21.62 8.82Q21.80 9 22.04 9.32L22.04 9.32L22.04 9.32Q22.29 9.63 22.57 10.27L22.57 10.27L22.57 10.27Q22.57 10.76 22.57 11.85L22.57 11.85L22.57 11.85Q22.57 12.94 22.57 14.36L22.57 14.36L22.57 14.36Q22.57 15.79 22.54 17.44L22.54 17.44L22.54 17.44Q22.50 19.09 22.48 20.76L22.48 20.76L22.48 20.76Q22.46 22.43 22.45 24.01L22.45 24.01L22.45 24.01Q22.43 25.59 22.39 26.82L22.39 26.82L22.39 26.82Q22.32 28.69 22.38 29.99L22.38 29.99L22.38 29.99Q22.43 31.29 22.59 32.15L22.59 32.15L22.59 32.15Q22.75 33.01 23.01 33.54L23.01 33.54L23.01 33.54Q23.27 34.07 23.66 34.38L23.66 34.38L23.66 34.38Q23.55 34.45 23.26 34.61L23.26 34.61L23.26 34.61Q22.96 34.77 22.54 34.93L22.54 34.93L22.54 34.93Q22.11 35.09 21.66 35.19L21.66 35.19L21.66 35.19Q21.20 35.30 20.85 35.30ZM32.55 32.70L32.55 32.70L32.55 32.70Q30.09 32.70 28.75 31.04L28.75 31.04L28.75 31.04Q27.42 29.39 27.42 26.72L27.42 26.72L27.42 26.72Q27.42 24.86 27.96 23.22L27.96 23.22L27.96 23.22Q28.51 21.59 29.42 20.39L29.42 20.39L29.42 20.39Q30.34 19.20 31.55 18.51L31.55 18.51L31.55 18.51Q32.76 17.82 34.10 17.82L34.10 17.82L34.10 17.82Q35.40 17.82 36.31 18.76L36.31 18.76L36.31 18.76Q37.23 19.69 37.23 21.41L37.23 21.41L37.23 21.41Q37.23 22.54 36.73 23.64L36.73 23.64L36.73 23.64Q36.24 24.75 35.45 25.61L35.45 25.61L35.45 25.61Q34.66 26.47 33.71 27L33.71 27L33.71 27Q32.76 27.53 31.88 27.53L31.88 27.53L31.88 27.53Q31.32 27.53 30.93 27.39L30.93 27.39L30.93 27.39Q30.55 27.25 30.23 26.96L30.23 26.96L30.23 26.96Q30.34 28.97 31.11 29.76L31.11 29.76L31.11 29.76Q31.88 30.55 33.11 30.55L33.11 30.55L33.11 30.55Q33.96 30.55 34.61 30.20L34.61 30.20L34.61 30.20Q35.26 29.85 35.71 29.39L35.71 29.39L35.71 29.39Q36.17 28.93 36.45 28.46L36.45 28.46L36.45 28.46Q36.73 27.98 36.84 27.74L36.84 27.74L36.84 27.74Q36.95 27.53 37.21 27.53L37.21 27.53L37.21 27.53Q37.47 27.53 37.47 27.95L37.47 27.95L37.47 27.95Q37.47 28.34 37.21 29.13L37.21 29.13L37.21 29.13Q36.95 29.92 36.35 30.71L36.35 30.71L36.35 30.71Q35.75 31.50 34.82 32.10L34.82 32.10L34.82 32.10Q33.89 32.70 32.55 32.70ZM34.17 19.34L34.17 19.34L34.17 19.34Q33.96 19.34 33.38 19.78L33.38 19.78L33.38 19.78Q32.80 20.21 32.15 21.06L32.15 21.06L32.15 21.06Q31.50 21.90 30.95 23.08L30.95 23.08L30.95 23.08Q30.41 24.26 30.27 25.73L30.27 25.73L30.27 25.73Q32.38 25.24 33.61 24.19L33.61 24.19L33.61 24.19Q34.84 23.13 34.84 21.09L34.84 21.09L34.84 21.09Q34.84 20.07 34.59 19.71L34.59 19.71L34.59 19.71Q34.34 19.34 34.17 19.34ZM46.22 32.70L46.22 32.70L46.22 32.70Q43.76 32.70 42.43 31.04L42.43 31.04L42.43 31.04Q41.09 29.39 41.09 26.72L41.09 26.72L41.09 26.72Q41.09 24.86 41.63 23.22L41.63 23.22L41.63 23.22Q42.18 21.59 43.09 20.39L43.09 20.39L43.09 20.39Q44.01 19.20 45.22 18.51L45.22 18.51L45.22 18.51Q46.43 17.82 47.77 17.82L47.77 17.82L47.77 17.82Q49.07 17.82 49.98 18.76L49.98 18.76L49.98 18.76Q50.90 19.69 50.90 21.41L50.90 21.41L50.90 21.41Q50.90 22.54 50.41 23.64L50.41 23.64L50.41 23.64Q49.91 24.75 49.12 25.61L49.12 25.61L49.12 25.61Q48.33 26.47 47.38 27L47.38 27L47.38 27Q46.43 27.53 45.55 27.53L45.55 27.53L45.55 27.53Q44.99 27.53 44.61 27.39L44.61 27.39L44.61 27.39Q44.22 27.25 43.90 26.96L43.90 26.96L43.90 26.96Q44.01 28.97 44.78 29.76L44.78 29.76L44.78 29.76Q45.55 30.55 46.79 30.55L46.79 30.55L46.79 30.55Q47.63 30.55 48.28 30.20L48.28 30.20L48.28 30.20Q48.93 29.85 49.39 29.39L49.39 29.39L49.39 29.39Q49.84 28.93 50.13 28.46L50.13 28.46L50.13 28.46Q50.41 27.98 50.51 27.74L50.51 27.74L50.51 27.74Q50.62 27.53 50.88 27.53L50.88 27.53L50.88 27.53Q51.14 27.53 51.14 27.95L51.14 27.95L51.14 27.95Q51.14 28.34 50.88 29.13L50.88 29.13L50.88 29.13Q50.62 29.92 50.02 30.71L50.02 30.71L50.02 30.71Q49.42 31.50 48.49 32.10L48.49 32.10L48.49 32.10Q47.56 32.70 46.22 32.70ZM47.84 19.34L47.84 19.34L47.84 19.34Q47.63 19.34 47.05 19.78L47.05 19.78L47.05 19.78Q46.47 20.21 45.82 21.06L45.82 21.06L45.82 21.06Q45.17 21.90 44.62 23.08L44.62 23.08L44.62 23.08Q44.08 24.26 43.94 25.73L43.94 25.73L43.94 25.73Q46.05 25.24 47.28 24.19L47.28 24.19L47.28 24.19Q48.51 23.13 48.51 21.09L48.51 21.09L48.51 21.09Q48.51 20.07 48.26 19.71L48.26 19.71L48.26 19.71Q48.02 19.34 47.84 19.34ZM68.40 16.66L68.40 16.66L68.40 16.66Q68.02 16.59 67.37 16.52L67.37 16.52L67.37 16.52Q66.71 16.45 66.05 16.42L66.05 16.42L66.05 16.42Q65.27 16.38 64.39 16.38L64.39 16.38L64.39 16.38Q63.52 16.31 62.20 16.37L62.20 16.37L62.20 16.37Q60.88 16.42 59.44 16.42L59.44 16.42L59.44 16.42Q59.40 17.33 59.31 18.28L59.31 18.28L59.31 18.28Q59.23 19.23 59.16 20.14L59.16 20.14L59.16 20.14Q58.98 21.83 58.86 23.59L58.86 23.59L58.86 23.59Q58.73 25.35 58.73 26.93L58.73 26.93L58.73 26.93Q58.73 28.51 58.93 29.79L58.93 29.79L58.93 29.79Q59.12 31.08 59.65 31.82L59.65 31.82L59.65 31.82Q59.23 32.13 58.73 32.29L58.73 32.29L58.73 32.29Q58.24 32.45 57.86 32.52L57.86 32.52L57.86 32.52Q57.40 32.63 56.94 32.63L56.94 32.63L56.94 32.63Q56.52 32.63 56.24 32.34L56.24 32.34L56.24 32.34Q55.96 32.06 55.92 31.36L55.92 31.36L55.92 31.36Q55.85 30.16 55.90 28.95L55.90 28.95L55.90 28.95Q55.96 27.74 56.04 26.40L56.04 26.40L56.04 26.40Q56.13 25.07 56.24 23.54L56.24 23.54L56.24 23.54Q56.34 22.01 56.45 20.18L56.45 20.18L56.45 20.18Q56.55 18.28 56.55 16.49L56.55 16.49L56.55 16.49Q55.22 16.52 54.30 16.52L54.30 16.52L54.30 16.52Q53.39 16.52 53.29 16.49L53.29 16.49L53.29 16.49Q53.18 16.42 53.04 16.17L53.04 16.17L53.04 16.17Q52.90 15.96 52.67 15.54L52.67 15.54L52.67 15.54Q52.44 15.12 52.09 14.34L52.09 14.34L52.09 14.34Q52.76 14.34 53.55 14.34L53.55 14.34L53.55 14.34Q54.34 14.34 55.01 14.31L55.01 14.31L55.01 14.31Q55.78 14.27 56.55 14.24L56.55 14.24L56.55 14.24Q56.52 13.68 56.52 13.04L56.52 13.04L56.52 13.04Q56.48 12.55 56.48 11.94L56.48 11.94L56.48 11.94Q56.48 11.32 56.45 10.79L56.45 10.79L56.45 10.79Q56.45 10.58 56.96 10.28L56.96 10.28L56.96 10.28Q57.47 9.98 58.07 9.74L58.07 9.74L58.07 9.74Q58.66 9.49 59.17 9.42L59.17 9.42L59.17 9.42Q59.68 9.35 59.68 9.67L59.68 9.67L59.68 9.67Q59.68 9.98 59.67 11.20L59.67 11.20L59.67 11.20Q59.65 12.41 59.58 14.13L59.58 14.13L59.58 14.13Q59.93 14.13 60.11 14.10L60.11 14.10L60.11 14.10Q61.97 14.03 63.11 13.94L63.11 13.94L63.11 13.94Q64.25 13.85 64.99 13.92L64.99 13.92L64.99 13.92Q65.73 13.99 66.29 14.27L66.29 14.27L66.29 14.27Q66.86 14.55 67.56 15.19L67.56 15.19L67.56 15.19Q67.80 15.40 67.98 15.64L67.98 15.64L67.98 15.64Q68.16 15.86 68.28 16.12L68.28 16.12L68.28 16.12Q68.40 16.38 68.40 16.66ZM72.48 19.23L72.48 19.23L72.48 19.23Q72.48 18.84 72.95 18.65L72.95 18.65L72.95 18.65Q73.43 18.46 73.97 18.46L73.97 18.46L73.97 18.46Q74.52 18.46 74.97 18.65L74.97 18.65L74.97 18.65Q75.43 18.84 75.39 19.16L75.39 19.16L75.39 19.16Q75.36 19.41 75.32 20.13L75.32 20.13L75.32 20.13Q75.29 20.85 75.24 21.83L75.24 21.83L75.24 21.83Q75.18 22.82 75.11 23.94L75.11 23.94L75.11 23.94Q75.04 25.07 74.99 26.14L74.99 26.14L74.99 26.14Q74.94 27.21 74.92 28.13L74.92 28.13L74.92 28.13Q74.90 29.04 74.90 29.53L74.90 29.53L74.90 29.53Q74.90 30.16 74.97 30.69L74.97 30.69L74.97 30.69Q75.04 31.15 75.15 31.59L75.15 31.59L75.15 31.59Q75.25 32.03 75.46 32.20L75.46 32.20L75.46 32.20Q75.18 32.31 74.88 32.34L74.88 32.34L74.88 32.34Q74.59 32.38 74.34 32.41L74.34 32.41L74.34 32.41Q74.06 32.45 73.78 32.45L73.78 32.45L73.78 32.45Q73.18 32.45 72.78 32.20L72.78 32.20L72.78 32.20Q72.37 31.96 72.37 31.18L72.37 31.18L72.37 31.18Q72.37 30.80 72.42 30.06L72.42 30.06L72.42 30.06Q72.48 29.32 72.55 28.48L72.55 28.48L72.55 28.48Q72.30 29.21 71.95 29.99L71.95 29.99L71.95 29.99Q71.60 30.76 71.09 31.36L71.09 31.36L71.09 31.36Q70.58 31.96 69.95 32.34L69.95 32.34L69.95 32.34Q69.31 32.73 68.50 32.73L68.50 32.73L68.50 32.73Q67.59 32.73 66.83 32.34L66.83 32.34L66.83 32.34Q66.08 31.96 65.52 31.24L65.52 31.24L65.52 31.24Q64.95 30.52 64.67 29.46L64.67 29.46L64.67 29.46Q64.39 28.41 64.46 27.04L64.46 27.04L64.46 27.04Q64.46 25.98 64.50 24.79L64.50 24.79L64.50 24.79Q64.53 23.77 64.60 22.43L64.60 22.43L64.60 22.43Q64.67 21.09 64.85 19.65L64.85 19.65L64.85 19.65Q64.88 19.51 65.34 19.30L65.34 19.30L65.34 19.30Q65.80 19.09 66.36 18.95L66.36 18.95L66.36 18.95Q66.92 18.81 67.45 18.84L67.45 18.84L67.45 18.84Q67.98 18.88 68.22 19.27L68.22 19.27L68.22 19.27Q68.05 19.90 67.87 20.76L67.87 20.76L67.87 20.76Q67.70 21.62 67.55 22.54L67.55 22.54L67.55 22.54Q67.41 23.45 67.34 24.33L67.34 24.33L67.34 24.33Q67.27 25.21 67.27 25.95L67.27 25.95L67.27 25.95Q67.27 26.61 67.34 27.40L67.34 27.40L67.34 27.40Q67.41 28.20 67.61 28.88L67.61 28.88L67.61 28.88Q67.80 29.57 68.17 30.02L68.17 30.02L68.17 30.02Q68.54 30.48 69.14 30.48L69.14 30.48L69.14 30.48Q69.80 30.48 70.31 29.90L70.31 29.90L70.31 29.90Q70.82 29.32 71.18 28.41L71.18 28.41L71.18 28.41Q71.53 27.49 71.77 26.31L71.77 26.31L71.77 26.31Q72.02 25.14 72.18 23.98L72.18 23.98L72.18 23.98Q72.34 22.82 72.39 21.74L72.39 21.74L72.39 21.74Q72.44 20.67 72.48 19.93L72.48 19.93L72.48 19.23ZM86.71 18.39L86.71 18.39L86.71 18.39Q87.70 18.39 88.54 18.95L88.54 18.95L88.54 18.95Q89.38 19.51 89.98 20.43L89.98 20.43L89.98 20.43Q90.58 21.34 90.93 22.52L90.93 22.52L90.93 22.52Q91.28 23.70 91.28 24.96L91.28 24.96L91.28 24.96Q91.28 26.23 91 27.63L91 27.63L91 27.63Q90.72 29.04 90.12 30.23L90.12 30.23L90.12 30.23Q89.52 31.43 88.57 32.22L88.57 32.22L88.57 32.22Q87.63 33.01 86.25 33.01L86.25 33.01L86.25 33.01Q85.16 33.01 84.20 32.22L84.20 32.22L84.20 32.22Q83.23 31.43 82.70 30.23L82.70 30.23L82.70 30.23Q82.63 31.46 82.39 32.75L82.39 32.75L82.39 32.75Q82.14 34.03 82.14 35.82L82.14 35.82L82.14 35.82Q82.14 36.35 82.18 37.02L82.18 37.02L82.18 37.02Q82.21 37.69 82.28 38.36L82.28 38.36L82.28 38.36Q82.35 39.02 82.49 39.59L82.49 39.59L82.49 39.59Q82.63 40.15 82.81 40.46L82.81 40.46L82.81 40.46Q82.21 40.82 81.74 40.99L81.74 40.99L81.74 40.99Q81.26 41.17 80.91 41.24L80.91 41.24L80.91 41.24Q80.52 41.34 80.17 41.34L80.17 41.34L80.17 41.34Q79.71 41.34 79.57 40.96L79.57 40.96L79.57 40.96Q79.43 40.57 79.43 39.59L79.43 39.59L79.43 39.59Q79.43 38.95 79.49 37.78L79.49 37.78L79.49 37.78Q79.54 36.60 79.63 35.09L79.63 35.09L79.63 35.09Q79.71 33.57 79.80 31.90L79.80 31.90L79.80 31.90Q79.89 30.23 79.98 28.62L79.98 28.62L79.98 28.62Q80.07 27 80.12 25.61L80.12 25.61L80.12 25.61Q80.17 24.22 80.17 23.27L80.17 23.27L80.17 23.27Q80.17 22.11 80.14 21.46L80.14 21.46L80.14 21.46Q80.10 20.81 80.05 20.48L80.05 20.48L80.05 20.48Q80.00 20.14 79.93 20.00L79.93 20.00L79.93 20.00Q79.86 19.86 79.75 19.69L79.75 19.69L79.75 19.69Q80.07 19.44 80.51 19.25L80.51 19.25L80.51 19.25Q80.95 19.05 81.38 18.97L81.38 18.97L81.38 18.97Q81.82 18.88 82.19 18.91L82.19 18.91L82.19 18.91Q82.56 18.95 82.77 19.09L82.77 19.09L82.77 19.09Q82.95 19.27 83.04 19.62L83.04 19.62L83.04 19.62Q83.13 19.97 83.20 20.67L83.20 20.67L83.20 20.67Q83.86 19.76 84.72 19.07L84.72 19.07L84.72 19.07Q85.59 18.39 86.71 18.39ZM85.34 30.94L85.34 30.94L85.34 30.94Q85.97 30.94 86.55 30.34L86.55 30.34L86.55 30.34Q87.13 29.74 87.57 28.81L87.57 28.81L87.57 28.81Q88.01 27.88 88.28 26.74L88.28 26.74L88.28 26.74Q88.54 25.59 88.54 24.47L88.54 24.47L88.54 24.47Q88.54 23.24 88.31 22.45L88.31 22.45L88.31 22.45Q88.08 21.66 87.75 21.18L87.75 21.18L87.75 21.18Q87.41 20.71 87.04 20.51L87.04 20.51L87.04 20.51Q86.68 20.32 86.39 20.32L86.39 20.32L86.39 20.32Q85.73 20.32 85.13 20.87L85.13 20.87L85.13 20.87Q84.53 21.41 84.07 22.27L84.07 22.27L84.07 22.27Q83.62 23.13 83.35 24.19L83.35 24.19L83.35 24.19Q83.09 25.24 83.09 26.30L83.09 26.30L83.09 26.30Q83.09 27.28 83.28 28.13L83.28 28.13L83.28 28.13Q83.48 28.97 83.79 29.60L83.79 29.60L83.79 29.60Q84.11 30.23 84.51 30.59L84.51 30.59L84.51 30.59Q84.92 30.94 85.34 30.94Z\"\r\n          transform=\"rotate(0 46.45374822616577 24.97499942779541)\"></path>\r\n  </svg>\r\n  </a>\r\n  <span class=\"filler\"></span>\r\n  <button mat-button routerLink=\"/login\">Log in</button>\r\n  <button mat-button [matMenuTriggerFor]=\"signUp\">Sign up</button>\r\n  <mat-menu #signUp=\"matMenu\">\r\n    <button mat-menu-item routerLink=\"/register-speaker\">As speaker</button>\r\n    <button mat-menu-item routerLink=\"/register\">As listener</button>\r\n  </mat-menu>\r\n</mat-toolbar>\r\n\r\n");

/***/ }),

/***/ "./node_modules/tslib/tslib.es6.js":
/*!*****************************************!*\
  !*** ./node_modules/tslib/tslib.es6.js ***!
  \*****************************************/
/*! exports provided: __extends, __assign, __rest, __decorate, __param, __metadata, __awaiter, __generator, __exportStar, __values, __read, __spread, __spreadArrays, __await, __asyncGenerator, __asyncDelegator, __asyncValues, __makeTemplateObject, __importStar, __importDefault */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__extends", function() { return __extends; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__assign", function() { return __assign; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__rest", function() { return __rest; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__decorate", function() { return __decorate; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__param", function() { return __param; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__metadata", function() { return __metadata; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__awaiter", function() { return __awaiter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__generator", function() { return __generator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__exportStar", function() { return __exportStar; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__values", function() { return __values; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__read", function() { return __read; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spread", function() { return __spread; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__spreadArrays", function() { return __spreadArrays; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__await", function() { return __await; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncGenerator", function() { return __asyncGenerator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncDelegator", function() { return __asyncDelegator; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__asyncValues", function() { return __asyncValues; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__makeTemplateObject", function() { return __makeTemplateObject; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importStar", function() { return __importStar; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "__importDefault", function() { return __importDefault; });
/*! *****************************************************************************
Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at http://www.apache.org/licenses/LICENSE-2.0

THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
MERCHANTABLITY OR NON-INFRINGEMENT.

See the Apache Version 2.0 License for specific language governing permissions
and limitations under the License.
***************************************************************************** */
/* global Reflect, Promise */

var extendStatics = function(d, b) {
    extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return extendStatics(d, b);
};

function __extends(d, b) {
    extendStatics(d, b);
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
}

var __assign = function() {
    __assign = Object.assign || function __assign(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
        }
        return t;
    }
    return __assign.apply(this, arguments);
}

function __rest(s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
}

function __decorate(decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
}

function __param(paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
}

function __metadata(metadataKey, metadataValue) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(metadataKey, metadataValue);
}

function __awaiter(thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
}

function __generator(thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
}

function __exportStar(m, exports) {
    for (var p in m) if (!exports.hasOwnProperty(p)) exports[p] = m[p];
}

function __values(o) {
    var m = typeof Symbol === "function" && o[Symbol.iterator], i = 0;
    if (m) return m.call(o);
    return {
        next: function () {
            if (o && i >= o.length) o = void 0;
            return { value: o && o[i++], done: !o };
        }
    };
}

function __read(o, n) {
    var m = typeof Symbol === "function" && o[Symbol.iterator];
    if (!m) return o;
    var i = m.call(o), r, ar = [], e;
    try {
        while ((n === void 0 || n-- > 0) && !(r = i.next()).done) ar.push(r.value);
    }
    catch (error) { e = { error: error }; }
    finally {
        try {
            if (r && !r.done && (m = i["return"])) m.call(i);
        }
        finally { if (e) throw e.error; }
    }
    return ar;
}

function __spread() {
    for (var ar = [], i = 0; i < arguments.length; i++)
        ar = ar.concat(__read(arguments[i]));
    return ar;
}

function __spreadArrays() {
    for (var s = 0, i = 0, il = arguments.length; i < il; i++) s += arguments[i].length;
    for (var r = Array(s), k = 0, i = 0; i < il; i++)
        for (var a = arguments[i], j = 0, jl = a.length; j < jl; j++, k++)
            r[k] = a[j];
    return r;
};

function __await(v) {
    return this instanceof __await ? (this.v = v, this) : new __await(v);
}

function __asyncGenerator(thisArg, _arguments, generator) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var g = generator.apply(thisArg, _arguments || []), i, q = [];
    return i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i;
    function verb(n) { if (g[n]) i[n] = function (v) { return new Promise(function (a, b) { q.push([n, v, a, b]) > 1 || resume(n, v); }); }; }
    function resume(n, v) { try { step(g[n](v)); } catch (e) { settle(q[0][3], e); } }
    function step(r) { r.value instanceof __await ? Promise.resolve(r.value.v).then(fulfill, reject) : settle(q[0][2], r); }
    function fulfill(value) { resume("next", value); }
    function reject(value) { resume("throw", value); }
    function settle(f, v) { if (f(v), q.shift(), q.length) resume(q[0][0], q[0][1]); }
}

function __asyncDelegator(o) {
    var i, p;
    return i = {}, verb("next"), verb("throw", function (e) { throw e; }), verb("return"), i[Symbol.iterator] = function () { return this; }, i;
    function verb(n, f) { i[n] = o[n] ? function (v) { return (p = !p) ? { value: __await(o[n](v)), done: n === "return" } : f ? f(v) : v; } : f; }
}

function __asyncValues(o) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var m = o[Symbol.asyncIterator], i;
    return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
    function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
    function settle(resolve, reject, d, v) { Promise.resolve(v).then(function(v) { resolve({ value: v, done: d }); }, reject); }
}

function __makeTemplateObject(cooked, raw) {
    if (Object.defineProperty) { Object.defineProperty(cooked, "raw", { value: raw }); } else { cooked.raw = raw; }
    return cooked;
};

function __importStar(mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result.default = mod;
    return result;
}

function __importDefault(mod) {
    return (mod && mod.__esModule) ? mod : { default: mod };
}


/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _register_speaker_register_speaker_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./register-speaker/register-speaker.component */ "./src/app/register-speaker/register-speaker.component.ts");







var routes = [
    { path: '', component: _home_home_component__WEBPACK_IMPORTED_MODULE_5__["HomeComponent"] },
    { path: 'login', component: _login_login_component__WEBPACK_IMPORTED_MODULE_4__["LoginComponent"] },
    { path: 'register', component: _register_register_component__WEBPACK_IMPORTED_MODULE_3__["RegisterComponent"] },
    { path: 'register-speaker', component: _register_speaker_register_speaker_component__WEBPACK_IMPORTED_MODULE_6__["RegisterSpeakerComponent"] },
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2FwcC5jb21wb25lbnQuY3NzIn0= */");

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'landing-page';
    }
    AppComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-root',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./app.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/app.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")).default]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./app-routing.module */ "./src/app/app-routing.module.ts");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _toolbar_menu_toolbar_menu_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./toolbar-menu/toolbar-menu.component */ "./src/app/toolbar-menu/toolbar-menu.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _login_login_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./login/login.component */ "./src/app/login/login.component.ts");
/* harmony import */ var _register_register_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./register/register.component */ "./src/app/register/register.component.ts");
/* harmony import */ var _home_home_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./home/home.component */ "./src/app/home/home.component.ts");
/* harmony import */ var _register_speaker_register_speaker_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./register-speaker/register-speaker.component */ "./src/app/register-speaker/register-speaker.component.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");















var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"],
                _toolbar_menu_toolbar_menu_component__WEBPACK_IMPORTED_MODULE_5__["ToolbarMenuComponent"],
                _login_login_component__WEBPACK_IMPORTED_MODULE_8__["LoginComponent"],
                _register_register_component__WEBPACK_IMPORTED_MODULE_9__["RegisterComponent"],
                _home_home_component__WEBPACK_IMPORTED_MODULE_10__["HomeComponent"],
                _register_speaker_register_speaker_component__WEBPACK_IMPORTED_MODULE_11__["RegisterSpeakerComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_1__["BrowserModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatInputModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_3__["AppRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatToolbarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatMenuModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_6__["MatGridListModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__["BrowserAnimationsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_12__["HttpClientModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_13__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_13__["ReactiveFormsModule"]
            ],
            schemas: [_angular_core__WEBPACK_IMPORTED_MODULE_2__["NO_ERRORS_SCHEMA"]],
            providers: [],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/home/home.component.css":
/*!*****************************************!*\
  !*** ./src/app/home/home.component.css ***!
  \*****************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("/*.responsive {*/\r\n  /*width: 100%;*/\r\n  /*height: auto;*/\r\n  /*}*/\r\n  /* Container needed to position the button. Adjust the width as needed */\r\n  .container {\r\n  position: relative;\r\n  width: 100%;\r\n}\r\n  /* Make the image responsive */\r\n  .container img {\r\n  width: 100%;\r\n  height: auto;\r\n}\r\n  /* Style the button and place it in the middle of the container/image */\r\n  .container .btn {\r\n  position: absolute;\r\n  top: 70%;\r\n  left: 50%;\r\n  transform: translate(-50%, -50%);\r\n  -ms-transform: translate(-50%, -50%);\r\n  background-color: lightgray;\r\n  opacity: 0.7;\r\n  color: white;\r\n  font-size: 18px;\r\n  padding: 13px 30px;\r\n  border: white;\r\n  cursor: pointer;\r\n  border-radius: 5px;\r\n\r\n}\r\n  .container .btn:hover {\r\n  background-color: red;\r\n  opacity: 1;\r\n}\r\n  .topics{\r\n  margin-left: 20%;\r\n  margin-right: 20%;\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvaG9tZS9ob21lLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUEsZ0JBQWdCO0VBQ2QsZUFBZTtFQUNmLGdCQUFnQjtFQUNsQixJQUFJO0VBQ0osd0VBQXdFO0VBQ3hFO0VBQ0Usa0JBQWtCO0VBQ2xCLFdBQVc7QUFDYjtFQUVBLDhCQUE4QjtFQUM5QjtFQUNFLFdBQVc7RUFDWCxZQUFZO0FBQ2Q7RUFFQSx1RUFBdUU7RUFDdkU7RUFDRSxrQkFBa0I7RUFDbEIsUUFBUTtFQUNSLFNBQVM7RUFDVCxnQ0FBZ0M7RUFDaEMsb0NBQW9DO0VBQ3BDLDJCQUEyQjtFQUMzQixZQUFZO0VBQ1osWUFBWTtFQUNaLGVBQWU7RUFDZixrQkFBa0I7RUFDbEIsYUFBYTtFQUNiLGVBQWU7RUFDZixrQkFBa0I7O0FBRXBCO0VBRUE7RUFDRSxxQkFBcUI7RUFDckIsVUFBVTtBQUNaO0VBQ0E7RUFDRSxnQkFBZ0I7RUFDaEIsaUJBQWlCO0FBQ25CIiwiZmlsZSI6InNyYy9hcHAvaG9tZS9ob21lLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIvKi5yZXNwb25zaXZlIHsqL1xyXG4gIC8qd2lkdGg6IDEwMCU7Ki9cclxuICAvKmhlaWdodDogYXV0bzsqL1xyXG4vKn0qL1xyXG4vKiBDb250YWluZXIgbmVlZGVkIHRvIHBvc2l0aW9uIHRoZSBidXR0b24uIEFkanVzdCB0aGUgd2lkdGggYXMgbmVlZGVkICovXHJcbi5jb250YWluZXIge1xyXG4gIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICB3aWR0aDogMTAwJTtcclxufVxyXG5cclxuLyogTWFrZSB0aGUgaW1hZ2UgcmVzcG9uc2l2ZSAqL1xyXG4uY29udGFpbmVyIGltZyB7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgaGVpZ2h0OiBhdXRvO1xyXG59XHJcblxyXG4vKiBTdHlsZSB0aGUgYnV0dG9uIGFuZCBwbGFjZSBpdCBpbiB0aGUgbWlkZGxlIG9mIHRoZSBjb250YWluZXIvaW1hZ2UgKi9cclxuLmNvbnRhaW5lciAuYnRuIHtcclxuICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgdG9wOiA3MCU7XHJcbiAgbGVmdDogNTAlO1xyXG4gIHRyYW5zZm9ybTogdHJhbnNsYXRlKC01MCUsIC01MCUpO1xyXG4gIC1tcy10cmFuc2Zvcm06IHRyYW5zbGF0ZSgtNTAlLCAtNTAlKTtcclxuICBiYWNrZ3JvdW5kLWNvbG9yOiBsaWdodGdyYXk7XHJcbiAgb3BhY2l0eTogMC43O1xyXG4gIGNvbG9yOiB3aGl0ZTtcclxuICBmb250LXNpemU6IDE4cHg7XHJcbiAgcGFkZGluZzogMTNweCAzMHB4O1xyXG4gIGJvcmRlcjogd2hpdGU7XHJcbiAgY3Vyc29yOiBwb2ludGVyO1xyXG4gIGJvcmRlci1yYWRpdXM6IDVweDtcclxuXHJcbn1cclxuXHJcbi5jb250YWluZXIgLmJ0bjpob3ZlciB7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogcmVkO1xyXG4gIG9wYWNpdHk6IDE7XHJcbn1cclxuLnRvcGljc3tcclxuICBtYXJnaW4tbGVmdDogMjAlO1xyXG4gIG1hcmdpbi1yaWdodDogMjAlO1xyXG59XHJcbiJdfQ== */");

/***/ }),

/***/ "./src/app/home/home.component.ts":
/*!****************************************!*\
  !*** ./src/app/home/home.component.ts ***!
  \****************************************/
/*! exports provided: HomeComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeComponent", function() { return HomeComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var HomeComponent = /** @class */ (function () {
    function HomeComponent() {
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    HomeComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-home',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./home.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/home/home.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./home.component.css */ "./src/app/home/home.component.css")).default]
        })
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "./src/app/login/login.component.css":
/*!*******************************************!*\
  !*** ./src/app/login/login.component.css ***!
  \*******************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("main {\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  width: 100vw;\r\n}\r\n\r\n.content {\r\n  margin-top: 60px;\r\n  width: 44%;\r\n  /* height: 416px; */\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  background: #FFFFFF;\r\n  box-shadow: 0px 5px 40px rgba(0, 45, 113, 0.06);\r\n  border-radius: 10px;\r\n  padding-top: 10px;\r\n  padding-bottom: 10px;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  .content {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 95%;\r\n  }\r\n}\r\n\r\nform {\r\n  width: 65%;\r\n  display: flex;\r\n  flex-direction: column;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  form {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  form {\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  form {\r\n    width: 95%;\r\n  }\r\n}\r\n\r\n.form__input {\r\n  background-color: #FCFCFC;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-size: 14px;\r\n  height: 40px;\r\n  padding-left: 14px;\r\n  margin-bottom: 24px;\r\n  border: 2px solid #F2F2F2;\r\n  border-radius: 2px;\r\n  outline: none;\r\n}\r\n\r\n.form__input:focus {\r\n  background-color: #fff;\r\n}\r\n\r\n.form__headline {\r\n  font-family: \"Exo 2\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: 600;\r\n  font-size: 28px;\r\n  line-height: 34px;\r\n  letter-spacing: 0.4px;\r\n  color: #322b6b;\r\n  margin-bottom: 25px;\r\n}\r\n\r\n.form__help {\r\n  align-self: flex-end;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: normal;\r\n  font-size: 12px;\r\n  line-height: 14px;\r\n  letter-spacing: 0.28px;\r\n  margin-top: -10px;\r\n  color: #222428;\r\n}\r\n\r\n.form__input::-webkit-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-moz-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-ms-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__button {\r\n  align-self: flex-end;\r\n  width: 47%;\r\n  height: 42px;\r\n  background-color: #0AD69C;\r\n  font-size: 16px;\r\n  font-family: \"Exo 2\", sans-serif;\r\n  color: #fff;\r\n  border: none;\r\n  border-radius: 2px;\r\n  cursor: pointer;\r\n  margin-top: 14px;\r\n}\r\n\r\n.form__button:disabled {\r\n  background: #dddddd;\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n.ng-invalid:not(form)  {\r\n  border-left: 5px solid #a94442; /* red */\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbG9naW4vbG9naW4uY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7QUFDZDs7QUFFQTtFQUNFLGdCQUFnQjtFQUNoQixVQUFVO0VBQ1YsbUJBQW1CO0VBQ25CLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLG1CQUFtQjtFQUNuQiwrQ0FBK0M7RUFDL0MsbUJBQW1CO0VBQ25CLGlCQUFpQjtFQUNqQixvQkFBb0I7QUFDdEI7O0FBRUE7RUFDRTtJQUNFLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0U7SUFDRSxnQkFBZ0I7SUFDaEIsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLGdCQUFnQjtJQUNoQixVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFLFVBQVU7RUFDVixhQUFhO0VBQ2Isc0JBQXNCO0FBQ3hCOztBQUVBO0VBQ0U7SUFDRSxVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFO0lBQ0UsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0UseUJBQXlCO0VBQ3pCLGlDQUFpQztFQUNqQyxlQUFlO0VBQ2YsWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixtQkFBbUI7RUFDbkIseUJBQXlCO0VBQ3pCLGtCQUFrQjtFQUNsQixhQUFhO0FBQ2Y7O0FBRUE7RUFDRSxzQkFBc0I7QUFDeEI7O0FBRUE7RUFDRSxnQ0FBZ0M7RUFDaEMsa0JBQWtCO0VBQ2xCLGdCQUFnQjtFQUNoQixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLHFCQUFxQjtFQUNyQixjQUFjO0VBQ2QsbUJBQW1CO0FBQ3JCOztBQUVBO0VBQ0Usb0JBQW9CO0VBQ3BCLGlDQUFpQztFQUNqQyxrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLGVBQWU7RUFDZixpQkFBaUI7RUFDakIsc0JBQXNCO0VBQ3RCLGlCQUFpQjtFQUNqQixjQUFjO0FBQ2hCOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQU1BO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0Usb0JBQW9CO0VBQ3BCLFVBQVU7RUFDVixZQUFZO0VBQ1oseUJBQXlCO0VBQ3pCLGVBQWU7RUFDZixnQ0FBZ0M7RUFDaEMsV0FBVztFQUNYLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZTtFQUNmLGdCQUFnQjtBQUNsQjs7QUFDQTtFQUNFLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFO0lBQ0Usa0JBQWtCO0VBQ3BCO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLGtCQUFrQjtFQUNwQjtBQUNGOztBQUVBO0VBQ0UsOEJBQThCLEVBQUUsUUFBUTtBQUMxQyIsImZpbGUiOiJzcmMvYXBwL2xvZ2luL2xvZ2luLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyJtYWluIHtcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgd2lkdGg6IDEwMHZ3O1xyXG59XHJcblxyXG4uY29udGVudCB7XHJcbiAgbWFyZ2luLXRvcDogNjBweDtcclxuICB3aWR0aDogNDQlO1xyXG4gIC8qIGhlaWdodDogNDE2cHg7ICovXHJcbiAgZGlzcGxheTogZmxleDtcclxuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gIGJhY2tncm91bmQ6ICNGRkZGRkY7XHJcbiAgYm94LXNoYWRvdzogMHB4IDVweCA0MHB4IHJnYmEoMCwgNDUsIDExMywgMC4wNik7XHJcbiAgYm9yZGVyLXJhZGl1czogMTBweDtcclxuICBwYWRkaW5nLXRvcDogMTBweDtcclxuICBwYWRkaW5nLWJvdHRvbTogMTBweDtcclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDkwMHB4KSB7XHJcbiAgLmNvbnRlbnQge1xyXG4gICAgd2lkdGg6IDcwJTtcclxuICB9XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA2MjBweCkge1xyXG4gIC5jb250ZW50IHtcclxuICAgIG1hcmdpbi10b3A6IDEwcHg7XHJcbiAgICB3aWR0aDogODAlO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDQ2MHB4KSB7XHJcbiAgLmNvbnRlbnQge1xyXG4gICAgbWFyZ2luLXRvcDogMTBweDtcclxuICAgIHdpZHRoOiA5NSU7XHJcbiAgfVxyXG59XHJcblxyXG5mb3JtIHtcclxuICB3aWR0aDogNjUlO1xyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAgZmxleC1kaXJlY3Rpb246IGNvbHVtbjtcclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDkwMHB4KSB7XHJcbiAgZm9ybSB7XHJcbiAgICB3aWR0aDogNzAlO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDYyMHB4KSB7XHJcbiAgZm9ybSB7XHJcbiAgICB3aWR0aDogODAlO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDQ2MHB4KSB7XHJcbiAgZm9ybSB7XHJcbiAgICB3aWR0aDogOTUlO1xyXG4gIH1cclxufVxyXG5cclxuLmZvcm1fX2lucHV0IHtcclxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjRkNGQ0ZDO1xyXG4gIGZvbnQtZmFtaWx5OiBcIlJvYm90b1wiLCBzYW5zLXNlcmlmO1xyXG4gIGZvbnQtc2l6ZTogMTRweDtcclxuICBoZWlnaHQ6IDQwcHg7XHJcbiAgcGFkZGluZy1sZWZ0OiAxNHB4O1xyXG4gIG1hcmdpbi1ib3R0b206IDI0cHg7XHJcbiAgYm9yZGVyOiAycHggc29saWQgI0YyRjJGMjtcclxuICBib3JkZXItcmFkaXVzOiAycHg7XHJcbiAgb3V0bGluZTogbm9uZTtcclxufVxyXG5cclxuLmZvcm1fX2lucHV0OmZvY3VzIHtcclxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjZmZmO1xyXG59XHJcblxyXG4uZm9ybV9faGVhZGxpbmUge1xyXG4gIGZvbnQtZmFtaWx5OiBcIkV4byAyXCIsIHNhbnMtc2VyaWY7XHJcbiAgZm9udC1zdHlsZTogbm9ybWFsO1xyXG4gIGZvbnQtd2VpZ2h0OiA2MDA7XHJcbiAgZm9udC1zaXplOiAyOHB4O1xyXG4gIGxpbmUtaGVpZ2h0OiAzNHB4O1xyXG4gIGxldHRlci1zcGFjaW5nOiAwLjRweDtcclxuICBjb2xvcjogIzMyMmI2YjtcclxuICBtYXJnaW4tYm90dG9tOiAyNXB4O1xyXG59XHJcblxyXG4uZm9ybV9faGVscCB7XHJcbiAgYWxpZ24tc2VsZjogZmxleC1lbmQ7XHJcbiAgZm9udC1mYW1pbHk6IFwiUm9ib3RvXCIsIHNhbnMtc2VyaWY7XHJcbiAgZm9udC1zdHlsZTogbm9ybWFsO1xyXG4gIGZvbnQtd2VpZ2h0OiBub3JtYWw7XHJcbiAgZm9udC1zaXplOiAxMnB4O1xyXG4gIGxpbmUtaGVpZ2h0OiAxNHB4O1xyXG4gIGxldHRlci1zcGFjaW5nOiAwLjI4cHg7XHJcbiAgbWFyZ2luLXRvcDogLTEwcHg7XHJcbiAgY29sb3I6ICMyMjI0Mjg7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6LXdlYmtpdC1pbnB1dC1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6LW1vei1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDotbXMtaW5wdXQtcGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6Oi1tcy1pbnB1dC1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6cGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9fYnV0dG9uIHtcclxuICBhbGlnbi1zZWxmOiBmbGV4LWVuZDtcclxuICB3aWR0aDogNDclO1xyXG4gIGhlaWdodDogNDJweDtcclxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMEFENjlDO1xyXG4gIGZvbnQtc2l6ZTogMTZweDtcclxuICBmb250LWZhbWlseTogXCJFeG8gMlwiLCBzYW5zLXNlcmlmO1xyXG4gIGNvbG9yOiAjZmZmO1xyXG4gIGJvcmRlcjogbm9uZTtcclxuICBib3JkZXItcmFkaXVzOiAycHg7XHJcbiAgY3Vyc29yOiBwb2ludGVyO1xyXG4gIG1hcmdpbi10b3A6IDE0cHg7XHJcbn1cclxuLmZvcm1fX2J1dHRvbjpkaXNhYmxlZCB7XHJcbiAgYmFja2dyb3VuZDogI2RkZGRkZDtcclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDYyMHB4KSB7XHJcbiAgLmZvcm1fX2J1dHRvbiB7XHJcbiAgICBhbGlnbi1zZWxmOiBjZW50ZXI7XHJcbiAgfVxyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogNDYwcHgpIHtcclxuICAuZm9ybV9fYnV0dG9uIHtcclxuICAgIGFsaWduLXNlbGY6IGNlbnRlcjtcclxuICB9XHJcbn1cclxuXHJcbi5uZy1pbnZhbGlkOm5vdChmb3JtKSAge1xyXG4gIGJvcmRlci1sZWZ0OiA1cHggc29saWQgI2E5NDQ0MjsgLyogcmVkICovXHJcbn1cclxuIl19 */");

/***/ }),

/***/ "./src/app/login/login.component.ts":
/*!******************************************!*\
  !*** ./src/app/login/login.component.ts ***!
  \******************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");




var LoginComponent = /** @class */ (function () {
    function LoginComponent(httpClient, formBuilder) {
        this.httpClient = httpClient;
        this.formBuilder = formBuilder;
        this.submitted = false;
    }
    LoginComponent.prototype.onSubmit = function () {
        this.do_login();
    };
    LoginComponent.prototype.do_login = function () {
        var user = {
            login: this.loginForm.get('login').value,
            password: this.loginForm.get('password').value
        };
        this.httpClient.post("/api/v1/user/login", user).subscribe();
    };
    LoginComponent.prototype.ngOnInit = function () {
        this.loginForm = this.formBuilder.group({
            login: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required],
            password: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].minLength(8)]],
        });
    };
    LoginComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] },
        { type: _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormBuilder"] }
    ]; };
    LoginComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./login.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/login/login.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./login.component.css */ "./src/app/login/login.component.css")).default]
        })
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/register-speaker/register-speaker.component.css":
/*!*****************************************************************!*\
  !*** ./src/app/register-speaker/register-speaker.component.css ***!
  \*****************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("main {\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  width: 100vw;\r\n}\r\n\r\n.content {\r\n  margin-top: 60px;\r\n  width: 44%;\r\n  /* height: 416px; */\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  background: #FFFFFF;\r\n  box-shadow: 0px 5px 40px rgba(0, 45, 113, 0.06);\r\n  border-radius: 10px;\r\n  padding-top: 10px;\r\n  padding-bottom: 10px;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  .content {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 95%;\r\n  }\r\n}\r\n\r\nform {\r\n  width: 65%;\r\n  display: flex;\r\n  flex-direction: column;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  form {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  form {\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  form {\r\n    width: 95%;\r\n  }\r\n}\r\n\r\n.form__input {\r\n  background-color: #FCFCFC;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-size: 14px;\r\n  height: 40px;\r\n  padding-left: 14px;\r\n  margin-bottom: 24px;\r\n  border: 2px solid #F2F2F2;\r\n  border-radius: 2px;\r\n  outline: none;\r\n}\r\n\r\n.form__input:focus {\r\n  background-color: #fff;\r\n}\r\n\r\n.form__headline {\r\n  font-family: \"Exo 2\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: 600;\r\n  font-size: 28px;\r\n  line-height: 34px;\r\n  letter-spacing: 0.4px;\r\n  color: #322b6b;\r\n  margin-bottom: 25px;\r\n}\r\n\r\n.form__help {\r\n  align-self: flex-end;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: normal;\r\n  font-size: 12px;\r\n  line-height: 14px;\r\n  letter-spacing: 0.28px;\r\n  margin-top: -10px;\r\n  color: #222428;\r\n}\r\n\r\n.form__input::-webkit-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-moz-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-ms-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__button {\r\n  align-self: flex-end;\r\n  width: 47%;\r\n  height: 42px;\r\n  background-color: #0AD69C;\r\n  font-size: 16px;\r\n  font-family: \"Exo 2\", sans-serif;\r\n  color: #fff;\r\n  border: none;\r\n  border-radius: 2px;\r\n  cursor: pointer;\r\n  margin-top: 14px;\r\n}\r\n\r\n.form__button:disabled {\r\n  background: #dddddd;\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n.complexity {\r\n  height: 6px;\r\n  justify-content: space-between;\r\n  margin-top: -20px;\r\n  margin-bottom: 20px;\r\n  display: none;\r\n}\r\n\r\n.active {\r\n  display: flex;\r\n}\r\n\r\n.complexity__item {\r\n  width: 32%;\r\n  height: 6px;\r\n  background-color: #F2F2F2;\r\n  border-radius: 2px;\r\n}\r\n\r\n.complexity__item--bad {\r\n  background: #FF6359;\r\n}\r\n\r\n.complexity__item--good {\r\n  background: #FFB966;\r\n}\r\n\r\n.complexity__item--perfect {\r\n  background: #38ECAC;\r\n}\r\n\r\n.ng-valid[required], .ng-valid.required  {\r\n  border-left: 5px solid #38ECAC; /* green */\r\n}\r\n\r\n.ng-invalid:not(form)  {\r\n  border-left: 5px solid #a94442; /* red */\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcmVnaXN0ZXItc3BlYWtlci9yZWdpc3Rlci1zcGVha2VyLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDRSxhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixZQUFZO0FBQ2Q7O0FBRUE7RUFDRSxnQkFBZ0I7RUFDaEIsVUFBVTtFQUNWLG1CQUFtQjtFQUNuQixhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixtQkFBbUI7RUFDbkIsK0NBQStDO0VBQy9DLG1CQUFtQjtFQUNuQixpQkFBaUI7RUFDakIsb0JBQW9CO0FBQ3RCOztBQUVBO0VBQ0U7SUFDRSxVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFO0lBQ0UsZ0JBQWdCO0lBQ2hCLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0U7SUFDRSxnQkFBZ0I7SUFDaEIsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRSxVQUFVO0VBQ1YsYUFBYTtFQUNiLHNCQUFzQjtBQUN4Qjs7QUFFQTtFQUNFO0lBQ0UsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0U7SUFDRSxVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFLHlCQUF5QjtFQUN6QixpQ0FBaUM7RUFDakMsZUFBZTtFQUNmLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLHlCQUF5QjtFQUN6QixrQkFBa0I7RUFDbEIsYUFBYTtBQUNmOztBQUVBO0VBQ0Usc0JBQXNCO0FBQ3hCOztBQUVBO0VBQ0UsZ0NBQWdDO0VBQ2hDLGtCQUFrQjtFQUNsQixnQkFBZ0I7RUFDaEIsZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixxQkFBcUI7RUFDckIsY0FBYztFQUNkLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFLG9CQUFvQjtFQUNwQixpQ0FBaUM7RUFDakMsa0JBQWtCO0VBQ2xCLG1CQUFtQjtFQUNuQixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLHNCQUFzQjtFQUN0QixpQkFBaUI7RUFDakIsY0FBYztBQUNoQjs7QUFFQTtFQUNFLGdDQUFnQztBQUNsQzs7QUFFQTtFQUNFLGdDQUFnQztBQUNsQzs7QUFNQTtFQUNFLGdDQUFnQztBQUNsQzs7QUFFQTtFQUNFLGdDQUFnQztBQUNsQzs7QUFFQTtFQUNFLG9CQUFvQjtFQUNwQixVQUFVO0VBQ1YsWUFBWTtFQUNaLHlCQUF5QjtFQUN6QixlQUFlO0VBQ2YsZ0NBQWdDO0VBQ2hDLFdBQVc7RUFDWCxZQUFZO0VBQ1osa0JBQWtCO0VBQ2xCLGVBQWU7RUFDZixnQkFBZ0I7QUFDbEI7O0FBQ0E7RUFDRSxtQkFBbUI7QUFDckI7O0FBRUE7RUFDRTtJQUNFLGtCQUFrQjtFQUNwQjtBQUNGOztBQUVBO0VBQ0U7SUFDRSxrQkFBa0I7RUFDcEI7QUFDRjs7QUFFQTtFQUNFLFdBQVc7RUFDWCw4QkFBOEI7RUFDOUIsaUJBQWlCO0VBQ2pCLG1CQUFtQjtFQUNuQixhQUFhO0FBQ2Y7O0FBRUE7RUFDRSxhQUFhO0FBQ2Y7O0FBRUE7RUFDRSxVQUFVO0VBQ1YsV0FBVztFQUNYLHlCQUF5QjtFQUN6QixrQkFBa0I7QUFDcEI7O0FBRUE7RUFDRSxtQkFBbUI7QUFDckI7O0FBRUE7RUFDRSxtQkFBbUI7QUFDckI7O0FBRUE7RUFDRSxtQkFBbUI7QUFDckI7O0FBRUE7RUFDRSw4QkFBOEIsRUFBRSxVQUFVO0FBQzVDOztBQUVBO0VBQ0UsOEJBQThCLEVBQUUsUUFBUTtBQUMxQyIsImZpbGUiOiJzcmMvYXBwL3JlZ2lzdGVyLXNwZWFrZXIvcmVnaXN0ZXItc3BlYWtlci5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsibWFpbiB7XHJcbiAgZGlzcGxheTogZmxleDtcclxuICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gIHdpZHRoOiAxMDB2dztcclxufVxyXG5cclxuLmNvbnRlbnQge1xyXG4gIG1hcmdpbi10b3A6IDYwcHg7XHJcbiAgd2lkdGg6IDQ0JTtcclxuICAvKiBoZWlnaHQ6IDQxNnB4OyAqL1xyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICBiYWNrZ3JvdW5kOiAjRkZGRkZGO1xyXG4gIGJveC1zaGFkb3c6IDBweCA1cHggNDBweCByZ2JhKDAsIDQ1LCAxMTMsIDAuMDYpO1xyXG4gIGJvcmRlci1yYWRpdXM6IDEwcHg7XHJcbiAgcGFkZGluZy10b3A6IDEwcHg7XHJcbiAgcGFkZGluZy1ib3R0b206IDEwcHg7XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA5MDBweCkge1xyXG4gIC5jb250ZW50IHtcclxuICAgIHdpZHRoOiA3MCU7XHJcbiAgfVxyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogNjIwcHgpIHtcclxuICAuY29udGVudCB7XHJcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xyXG4gICAgd2lkdGg6IDgwJTtcclxuICB9XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA0NjBweCkge1xyXG4gIC5jb250ZW50IHtcclxuICAgIG1hcmdpbi10b3A6IDEwcHg7XHJcbiAgICB3aWR0aDogOTUlO1xyXG4gIH1cclxufVxyXG5cclxuZm9ybSB7XHJcbiAgd2lkdGg6IDY1JTtcclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGZsZXgtZGlyZWN0aW9uOiBjb2x1bW47XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA5MDBweCkge1xyXG4gIGZvcm0ge1xyXG4gICAgd2lkdGg6IDcwJTtcclxuICB9XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA2MjBweCkge1xyXG4gIGZvcm0ge1xyXG4gICAgd2lkdGg6IDgwJTtcclxuICB9XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA0NjBweCkge1xyXG4gIGZvcm0ge1xyXG4gICAgd2lkdGg6IDk1JTtcclxuICB9XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dCB7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogI0ZDRkNGQztcclxuICBmb250LWZhbWlseTogXCJSb2JvdG9cIiwgc2Fucy1zZXJpZjtcclxuICBmb250LXNpemU6IDE0cHg7XHJcbiAgaGVpZ2h0OiA0MHB4O1xyXG4gIHBhZGRpbmctbGVmdDogMTRweDtcclxuICBtYXJnaW4tYm90dG9tOiAyNHB4O1xyXG4gIGJvcmRlcjogMnB4IHNvbGlkICNGMkYyRjI7XHJcbiAgYm9yZGVyLXJhZGl1czogMnB4O1xyXG4gIG91dGxpbmU6IG5vbmU7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDpmb2N1cyB7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogI2ZmZjtcclxufVxyXG5cclxuLmZvcm1fX2hlYWRsaW5lIHtcclxuICBmb250LWZhbWlseTogXCJFeG8gMlwiLCBzYW5zLXNlcmlmO1xyXG4gIGZvbnQtc3R5bGU6IG5vcm1hbDtcclxuICBmb250LXdlaWdodDogNjAwO1xyXG4gIGZvbnQtc2l6ZTogMjhweDtcclxuICBsaW5lLWhlaWdodDogMzRweDtcclxuICBsZXR0ZXItc3BhY2luZzogMC40cHg7XHJcbiAgY29sb3I6ICMzMjJiNmI7XHJcbiAgbWFyZ2luLWJvdHRvbTogMjVweDtcclxufVxyXG5cclxuLmZvcm1fX2hlbHAge1xyXG4gIGFsaWduLXNlbGY6IGZsZXgtZW5kO1xyXG4gIGZvbnQtZmFtaWx5OiBcIlJvYm90b1wiLCBzYW5zLXNlcmlmO1xyXG4gIGZvbnQtc3R5bGU6IG5vcm1hbDtcclxuICBmb250LXdlaWdodDogbm9ybWFsO1xyXG4gIGZvbnQtc2l6ZTogMTJweDtcclxuICBsaW5lLWhlaWdodDogMTRweDtcclxuICBsZXR0ZXItc3BhY2luZzogMC4yOHB4O1xyXG4gIG1hcmdpbi10b3A6IC0xMHB4O1xyXG4gIGNvbG9yOiAjMjIyNDI4O1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6Oi13ZWJraXQtaW5wdXQtcGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6Oi1tb3otcGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6LW1zLWlucHV0LXBsYWNlaG9sZGVyIHtcclxuICBjb2xvcjogcmdiYSgxMzUsIDE0NCwgMTY3LCAwLjU3KTtcclxufVxyXG5cclxuLmZvcm1fX2lucHV0OjotbXMtaW5wdXQtcGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6OnBsYWNlaG9sZGVyIHtcclxuICBjb2xvcjogcmdiYSgxMzUsIDE0NCwgMTY3LCAwLjU3KTtcclxufVxyXG5cclxuLmZvcm1fX2J1dHRvbiB7XHJcbiAgYWxpZ24tc2VsZjogZmxleC1lbmQ7XHJcbiAgd2lkdGg6IDQ3JTtcclxuICBoZWlnaHQ6IDQycHg7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogIzBBRDY5QztcclxuICBmb250LXNpemU6IDE2cHg7XHJcbiAgZm9udC1mYW1pbHk6IFwiRXhvIDJcIiwgc2Fucy1zZXJpZjtcclxuICBjb2xvcjogI2ZmZjtcclxuICBib3JkZXI6IG5vbmU7XHJcbiAgYm9yZGVyLXJhZGl1czogMnB4O1xyXG4gIGN1cnNvcjogcG9pbnRlcjtcclxuICBtYXJnaW4tdG9wOiAxNHB4O1xyXG59XHJcbi5mb3JtX19idXR0b246ZGlzYWJsZWQge1xyXG4gIGJhY2tncm91bmQ6ICNkZGRkZGQ7XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA2MjBweCkge1xyXG4gIC5mb3JtX19idXR0b24ge1xyXG4gICAgYWxpZ24tc2VsZjogY2VudGVyO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDQ2MHB4KSB7XHJcbiAgLmZvcm1fX2J1dHRvbiB7XHJcbiAgICBhbGlnbi1zZWxmOiBjZW50ZXI7XHJcbiAgfVxyXG59XHJcblxyXG4uY29tcGxleGl0eSB7XHJcbiAgaGVpZ2h0OiA2cHg7XHJcbiAganVzdGlmeS1jb250ZW50OiBzcGFjZS1iZXR3ZWVuO1xyXG4gIG1hcmdpbi10b3A6IC0yMHB4O1xyXG4gIG1hcmdpbi1ib3R0b206IDIwcHg7XHJcbiAgZGlzcGxheTogbm9uZTtcclxufVxyXG5cclxuLmFjdGl2ZSB7XHJcbiAgZGlzcGxheTogZmxleDtcclxufVxyXG5cclxuLmNvbXBsZXhpdHlfX2l0ZW0ge1xyXG4gIHdpZHRoOiAzMiU7XHJcbiAgaGVpZ2h0OiA2cHg7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogI0YyRjJGMjtcclxuICBib3JkZXItcmFkaXVzOiAycHg7XHJcbn1cclxuXHJcbi5jb21wbGV4aXR5X19pdGVtLS1iYWQge1xyXG4gIGJhY2tncm91bmQ6ICNGRjYzNTk7XHJcbn1cclxuXHJcbi5jb21wbGV4aXR5X19pdGVtLS1nb29kIHtcclxuICBiYWNrZ3JvdW5kOiAjRkZCOTY2O1xyXG59XHJcblxyXG4uY29tcGxleGl0eV9faXRlbS0tcGVyZmVjdCB7XHJcbiAgYmFja2dyb3VuZDogIzM4RUNBQztcclxufVxyXG5cclxuLm5nLXZhbGlkW3JlcXVpcmVkXSwgLm5nLXZhbGlkLnJlcXVpcmVkICB7XHJcbiAgYm9yZGVyLWxlZnQ6IDVweCBzb2xpZCAjMzhFQ0FDOyAvKiBncmVlbiAqL1xyXG59XHJcblxyXG4ubmctaW52YWxpZDpub3QoZm9ybSkgIHtcclxuICBib3JkZXItbGVmdDogNXB4IHNvbGlkICNhOTQ0NDI7IC8qIHJlZCAqL1xyXG59XHJcbiJdfQ== */");

/***/ }),

/***/ "./src/app/register-speaker/register-speaker.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/register-speaker/register-speaker.component.ts ***!
  \****************************************************************/
/*! exports provided: RegisterSpeakerComponent, MustMatch */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterSpeakerComponent", function() { return RegisterSpeakerComponent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MustMatch", function() { return MustMatch; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");




var RegisterSpeakerComponent = /** @class */ (function () {
    function RegisterSpeakerComponent(httpClient, formBuilder) {
        this.httpClient = httpClient;
        this.formBuilder = formBuilder;
        this.submitted = false;
    }
    RegisterSpeakerComponent.prototype.onSubmit = function () {
        this.register();
    };
    RegisterSpeakerComponent.prototype.register = function () {
        var user = {
            login: this.registerForm.get('login').value,
            email: this.registerForm.get('email').value,
            password: this.registerForm.get('password').value
        };
        this.httpClient.post("/api/v1/user/register/speaker", user).subscribe();
    };
    Object.defineProperty(RegisterSpeakerComponent.prototype, "form", {
        get: function () {
            return this.registerForm.controls;
        },
        enumerable: true,
        configurable: true
    });
    RegisterSpeakerComponent.prototype.ngOnInit = function () {
        this.registerForm = this.formBuilder.group({
            login: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required],
            email: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].email]],
            password: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].minLength(8)]],
            confirmPassword: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required]
        }, {
            validator: MustMatch('password', 'confirmPassword')
        });
        // TODO: change to password-strength
        var pass = document.querySelector('.password');
        var complexityItem = document.getElementsByClassName('complexity__item');
        var complexity = document.querySelector('.complexity');
        var content = document.querySelector('.content');
        content.addEventListener('click', function (event) {
            // Toggle class "is-active"
            if (event.target === pass) {
                complexity.classList.add('active');
            }
            else {
                complexity.classList.remove('active');
            }
            pass.addEventListener('input', function (eve) {
                var lengthOfPass = eve.target.value.split('').length;
                if (lengthOfPass == 0) {
                    complexity.classList.remove('active');
                }
                else if (lengthOfPass <= 7) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--good');
                        complexityItem[i].classList.remove('complexity__item--perfect');
                    }
                    complexityItem[0].classList.add('complexity__item--bad');
                }
                else if (lengthOfPass <= 8 && eve.target.value.match(/[A-Z]/i)) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--bad');
                        complexityItem[i].classList.remove('complexity__item--perfect');
                    }
                    for (var i = 0; i < complexityItem.length - 1; i++) {
                        complexityItem[i].classList.add('complexity__item--good');
                    }
                }
                else if (lengthOfPass > 8 && eve.target.value.match(/[A-Z]/i) && eve.target.value.match(/\d/i) && eve.target.value.match(/[a-z]/i)) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--bad');
                        complexityItem[i].classList.remove('complexity__item--good');
                    }
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.add('complexity__item--perfect');
                    }
                }
            });
        });
    };
    RegisterSpeakerComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] },
        { type: _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormBuilder"] }
    ]; };
    RegisterSpeakerComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-register-speaker',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./register-speaker.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/register-speaker/register-speaker.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./register-speaker.component.css */ "./src/app/register-speaker/register-speaker.component.css")).default]
        })
    ], RegisterSpeakerComponent);
    return RegisterSpeakerComponent;
}());

function MustMatch(controlName, matchingControlName) {
    return function (formGroup) {
        var control = formGroup.controls[controlName];
        var matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors.mustMatch) {
            // return if another validator has already found an error on the matchingControl
            return;
        }
        // set error on matchingControl if validation fails
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({ mustMatch: true });
        }
        else {
            matchingControl.setErrors(null);
        }
    };
}


/***/ }),

/***/ "./src/app/register/register.component.css":
/*!*************************************************!*\
  !*** ./src/app/register/register.component.css ***!
  \*************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("main {\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  width: 100vw;\r\n}\r\n\r\n.content {\r\n  margin-top: 60px;\r\n  width: 44%;\r\n  /* height: 416px; */\r\n  display: flex;\r\n  justify-content: center;\r\n  align-items: center;\r\n  background: #FFFFFF;\r\n  box-shadow: 0px 5px 40px rgba(0, 45, 113, 0.06);\r\n  border-radius: 10px;\r\n  padding-top: 10px;\r\n  padding-bottom: 10px;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  .content {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .content {\r\n    margin-top: 10px;\r\n    width: 95%;\r\n  }\r\n}\r\n\r\nform {\r\n  width: 65%;\r\n  display: flex;\r\n  flex-direction: column;\r\n}\r\n\r\n@media (max-width: 900px) {\r\n  form {\r\n    width: 70%;\r\n  }\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  form {\r\n    width: 80%;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  form {\r\n    width: 95%;\r\n  }\r\n}\r\n\r\n.form__input {\r\n  background-color: #FCFCFC;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-size: 14px;\r\n  height: 40px;\r\n  padding-left: 14px;\r\n  margin-bottom: 24px;\r\n  border: 2px solid #F2F2F2;\r\n  border-radius: 2px;\r\n  outline: none;\r\n}\r\n\r\n.form__input:focus {\r\n  background-color: #fff;\r\n}\r\n\r\n.form__user {\r\n  display: flex;\r\n}\r\n\r\n.form__user .form__input {\r\n  width: 47.5%;\r\n}\r\n\r\n.form__user .form__input:first-child {\r\n  margin-right: 5%;\r\n}\r\n\r\n.form__headline {\r\n  font-family: \"Exo 2\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: 600;\r\n  font-size: 28px;\r\n  line-height: 34px;\r\n  letter-spacing: 0.4px;\r\n  color: #322b6b;\r\n  margin-bottom: 25px;\r\n}\r\n\r\n.form__help {\r\n  align-self: flex-end;\r\n  font-family: \"Roboto\", sans-serif;\r\n  font-style: normal;\r\n  font-weight: normal;\r\n  font-size: 12px;\r\n  line-height: 14px;\r\n  letter-spacing: 0.28px;\r\n  margin-top: -10px;\r\n  color: #222428;\r\n}\r\n\r\n.form__input::-webkit-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-moz-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::-ms-input-placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__input::placeholder {\r\n  color: rgba(135, 144, 167, 0.57);\r\n}\r\n\r\n.form__button {\r\n  align-self: flex-end;\r\n  width: 47%;\r\n  height: 42px;\r\n  background-color: #0AD69C;\r\n  font-size: 16px;\r\n  font-family: \"Exo 2\", sans-serif;\r\n  color: #fff;\r\n  border: none;\r\n  border-radius: 2px;\r\n  cursor: pointer;\r\n  margin-top: 14px;\r\n}\r\n\r\n.form__button:disabled {\r\n  background: #dddddd;\r\n}\r\n\r\n@media (max-width: 620px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n@media (max-width: 460px) {\r\n  .form__button {\r\n    align-self: center;\r\n  }\r\n}\r\n\r\n.complexity {\r\n  height: 6px;\r\n  justify-content: space-between;\r\n  margin-top: -20px;\r\n  margin-bottom: 20px;\r\n  display: none;\r\n}\r\n\r\n.active {\r\n  display: flex;\r\n}\r\n\r\n.complexity__item {\r\n  width: 32%;\r\n  height: 6px;\r\n  background-color: #F2F2F2;\r\n  border-radius: 2px;\r\n}\r\n\r\n.complexity__item--bad {\r\n  background: #FF6359;\r\n}\r\n\r\n.complexity__item--good {\r\n  background: #FFB966;\r\n}\r\n\r\n.complexity__item--perfect {\r\n  background: #38ECAC;\r\n}\r\n\r\n.ng-valid[required], .ng-valid.required {\r\n  border-left: 5px solid #38ECAC; /* green */\r\n}\r\n\r\n.ng-invalid:not(form) {\r\n  border-left: 5px solid #a94442; /* red */\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcmVnaXN0ZXIvcmVnaXN0ZXIuY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7QUFDZDs7QUFFQTtFQUNFLGdCQUFnQjtFQUNoQixVQUFVO0VBQ1YsbUJBQW1CO0VBQ25CLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLG1CQUFtQjtFQUNuQiwrQ0FBK0M7RUFDL0MsbUJBQW1CO0VBQ25CLGlCQUFpQjtFQUNqQixvQkFBb0I7QUFDdEI7O0FBRUE7RUFDRTtJQUNFLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0U7SUFDRSxnQkFBZ0I7SUFDaEIsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLGdCQUFnQjtJQUNoQixVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFLFVBQVU7RUFDVixhQUFhO0VBQ2Isc0JBQXNCO0FBQ3hCOztBQUVBO0VBQ0U7SUFDRSxVQUFVO0VBQ1o7QUFDRjs7QUFFQTtFQUNFO0lBQ0UsVUFBVTtFQUNaO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLFVBQVU7RUFDWjtBQUNGOztBQUVBO0VBQ0UseUJBQXlCO0VBQ3pCLGlDQUFpQztFQUNqQyxlQUFlO0VBQ2YsWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixtQkFBbUI7RUFDbkIseUJBQXlCO0VBQ3pCLGtCQUFrQjtFQUNsQixhQUFhO0FBQ2Y7O0FBRUE7RUFDRSxzQkFBc0I7QUFDeEI7O0FBRUE7RUFDRSxhQUFhO0FBQ2Y7O0FBRUE7RUFDRSxZQUFZO0FBQ2Q7O0FBRUE7RUFDRSxnQkFBZ0I7QUFDbEI7O0FBRUE7RUFDRSxnQ0FBZ0M7RUFDaEMsa0JBQWtCO0VBQ2xCLGdCQUFnQjtFQUNoQixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLHFCQUFxQjtFQUNyQixjQUFjO0VBQ2QsbUJBQW1CO0FBQ3JCOztBQUVBO0VBQ0Usb0JBQW9CO0VBQ3BCLGlDQUFpQztFQUNqQyxrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLGVBQWU7RUFDZixpQkFBaUI7RUFDakIsc0JBQXNCO0VBQ3RCLGlCQUFpQjtFQUNqQixjQUFjO0FBQ2hCOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQU1BO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0UsZ0NBQWdDO0FBQ2xDOztBQUVBO0VBQ0Usb0JBQW9CO0VBQ3BCLFVBQVU7RUFDVixZQUFZO0VBQ1oseUJBQXlCO0VBQ3pCLGVBQWU7RUFDZixnQ0FBZ0M7RUFDaEMsV0FBVztFQUNYLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZTtFQUNmLGdCQUFnQjtBQUNsQjs7QUFFQTtFQUNFLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFO0lBQ0Usa0JBQWtCO0VBQ3BCO0FBQ0Y7O0FBRUE7RUFDRTtJQUNFLGtCQUFrQjtFQUNwQjtBQUNGOztBQUVBO0VBQ0UsV0FBVztFQUNYLDhCQUE4QjtFQUM5QixpQkFBaUI7RUFDakIsbUJBQW1CO0VBQ25CLGFBQWE7QUFDZjs7QUFFQTtFQUNFLGFBQWE7QUFDZjs7QUFFQTtFQUNFLFVBQVU7RUFDVixXQUFXO0VBQ1gseUJBQXlCO0VBQ3pCLGtCQUFrQjtBQUNwQjs7QUFFQTtFQUNFLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFLG1CQUFtQjtBQUNyQjs7QUFFQTtFQUNFLDhCQUE4QixFQUFFLFVBQVU7QUFDNUM7O0FBRUE7RUFDRSw4QkFBOEIsRUFBRSxRQUFRO0FBQzFDIiwiZmlsZSI6InNyYy9hcHAvcmVnaXN0ZXIvcmVnaXN0ZXIuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIm1haW4ge1xyXG4gIGRpc3BsYXk6IGZsZXg7XHJcbiAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICB3aWR0aDogMTAwdnc7XHJcbn1cclxuXHJcbi5jb250ZW50IHtcclxuICBtYXJnaW4tdG9wOiA2MHB4O1xyXG4gIHdpZHRoOiA0NCU7XHJcbiAgLyogaGVpZ2h0OiA0MTZweDsgKi9cclxuICBkaXNwbGF5OiBmbGV4O1xyXG4gIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgYmFja2dyb3VuZDogI0ZGRkZGRjtcclxuICBib3gtc2hhZG93OiAwcHggNXB4IDQwcHggcmdiYSgwLCA0NSwgMTEzLCAwLjA2KTtcclxuICBib3JkZXItcmFkaXVzOiAxMHB4O1xyXG4gIHBhZGRpbmctdG9wOiAxMHB4O1xyXG4gIHBhZGRpbmctYm90dG9tOiAxMHB4O1xyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogOTAwcHgpIHtcclxuICAuY29udGVudCB7XHJcbiAgICB3aWR0aDogNzAlO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDYyMHB4KSB7XHJcbiAgLmNvbnRlbnQge1xyXG4gICAgbWFyZ2luLXRvcDogMTBweDtcclxuICAgIHdpZHRoOiA4MCU7XHJcbiAgfVxyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogNDYwcHgpIHtcclxuICAuY29udGVudCB7XHJcbiAgICBtYXJnaW4tdG9wOiAxMHB4O1xyXG4gICAgd2lkdGg6IDk1JTtcclxuICB9XHJcbn1cclxuXHJcbmZvcm0ge1xyXG4gIHdpZHRoOiA2NSU7XHJcbiAgZGlzcGxheTogZmxleDtcclxuICBmbGV4LWRpcmVjdGlvbjogY29sdW1uO1xyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogOTAwcHgpIHtcclxuICBmb3JtIHtcclxuICAgIHdpZHRoOiA3MCU7XHJcbiAgfVxyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogNjIwcHgpIHtcclxuICBmb3JtIHtcclxuICAgIHdpZHRoOiA4MCU7XHJcbiAgfVxyXG59XHJcblxyXG5AbWVkaWEgKG1heC13aWR0aDogNDYwcHgpIHtcclxuICBmb3JtIHtcclxuICAgIHdpZHRoOiA5NSU7XHJcbiAgfVxyXG59XHJcblxyXG4uZm9ybV9faW5wdXQge1xyXG4gIGJhY2tncm91bmQtY29sb3I6ICNGQ0ZDRkM7XHJcbiAgZm9udC1mYW1pbHk6IFwiUm9ib3RvXCIsIHNhbnMtc2VyaWY7XHJcbiAgZm9udC1zaXplOiAxNHB4O1xyXG4gIGhlaWdodDogNDBweDtcclxuICBwYWRkaW5nLWxlZnQ6IDE0cHg7XHJcbiAgbWFyZ2luLWJvdHRvbTogMjRweDtcclxuICBib3JkZXI6IDJweCBzb2xpZCAjRjJGMkYyO1xyXG4gIGJvcmRlci1yYWRpdXM6IDJweDtcclxuICBvdXRsaW5lOiBub25lO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6Zm9jdXMge1xyXG4gIGJhY2tncm91bmQtY29sb3I6ICNmZmY7XHJcbn1cclxuXHJcbi5mb3JtX191c2VyIHtcclxuICBkaXNwbGF5OiBmbGV4O1xyXG59XHJcblxyXG4uZm9ybV9fdXNlciAuZm9ybV9faW5wdXQge1xyXG4gIHdpZHRoOiA0Ny41JTtcclxufVxyXG5cclxuLmZvcm1fX3VzZXIgLmZvcm1fX2lucHV0OmZpcnN0LWNoaWxkIHtcclxuICBtYXJnaW4tcmlnaHQ6IDUlO1xyXG59XHJcblxyXG4uZm9ybV9faGVhZGxpbmUge1xyXG4gIGZvbnQtZmFtaWx5OiBcIkV4byAyXCIsIHNhbnMtc2VyaWY7XHJcbiAgZm9udC1zdHlsZTogbm9ybWFsO1xyXG4gIGZvbnQtd2VpZ2h0OiA2MDA7XHJcbiAgZm9udC1zaXplOiAyOHB4O1xyXG4gIGxpbmUtaGVpZ2h0OiAzNHB4O1xyXG4gIGxldHRlci1zcGFjaW5nOiAwLjRweDtcclxuICBjb2xvcjogIzMyMmI2YjtcclxuICBtYXJnaW4tYm90dG9tOiAyNXB4O1xyXG59XHJcblxyXG4uZm9ybV9faGVscCB7XHJcbiAgYWxpZ24tc2VsZjogZmxleC1lbmQ7XHJcbiAgZm9udC1mYW1pbHk6IFwiUm9ib3RvXCIsIHNhbnMtc2VyaWY7XHJcbiAgZm9udC1zdHlsZTogbm9ybWFsO1xyXG4gIGZvbnQtd2VpZ2h0OiBub3JtYWw7XHJcbiAgZm9udC1zaXplOiAxMnB4O1xyXG4gIGxpbmUtaGVpZ2h0OiAxNHB4O1xyXG4gIGxldHRlci1zcGFjaW5nOiAwLjI4cHg7XHJcbiAgbWFyZ2luLXRvcDogLTEwcHg7XHJcbiAgY29sb3I6ICMyMjI0Mjg7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6LXdlYmtpdC1pbnB1dC1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6LW1vei1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDotbXMtaW5wdXQtcGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9faW5wdXQ6Oi1tcy1pbnB1dC1wbGFjZWhvbGRlciB7XHJcbiAgY29sb3I6IHJnYmEoMTM1LCAxNDQsIDE2NywgMC41Nyk7XHJcbn1cclxuXHJcbi5mb3JtX19pbnB1dDo6cGxhY2Vob2xkZXIge1xyXG4gIGNvbG9yOiByZ2JhKDEzNSwgMTQ0LCAxNjcsIDAuNTcpO1xyXG59XHJcblxyXG4uZm9ybV9fYnV0dG9uIHtcclxuICBhbGlnbi1zZWxmOiBmbGV4LWVuZDtcclxuICB3aWR0aDogNDclO1xyXG4gIGhlaWdodDogNDJweDtcclxuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMEFENjlDO1xyXG4gIGZvbnQtc2l6ZTogMTZweDtcclxuICBmb250LWZhbWlseTogXCJFeG8gMlwiLCBzYW5zLXNlcmlmO1xyXG4gIGNvbG9yOiAjZmZmO1xyXG4gIGJvcmRlcjogbm9uZTtcclxuICBib3JkZXItcmFkaXVzOiAycHg7XHJcbiAgY3Vyc29yOiBwb2ludGVyO1xyXG4gIG1hcmdpbi10b3A6IDE0cHg7XHJcbn1cclxuXHJcbi5mb3JtX19idXR0b246ZGlzYWJsZWQge1xyXG4gIGJhY2tncm91bmQ6ICNkZGRkZGQ7XHJcbn1cclxuXHJcbkBtZWRpYSAobWF4LXdpZHRoOiA2MjBweCkge1xyXG4gIC5mb3JtX19idXR0b24ge1xyXG4gICAgYWxpZ24tc2VsZjogY2VudGVyO1xyXG4gIH1cclxufVxyXG5cclxuQG1lZGlhIChtYXgtd2lkdGg6IDQ2MHB4KSB7XHJcbiAgLmZvcm1fX2J1dHRvbiB7XHJcbiAgICBhbGlnbi1zZWxmOiBjZW50ZXI7XHJcbiAgfVxyXG59XHJcblxyXG4uY29tcGxleGl0eSB7XHJcbiAgaGVpZ2h0OiA2cHg7XHJcbiAganVzdGlmeS1jb250ZW50OiBzcGFjZS1iZXR3ZWVuO1xyXG4gIG1hcmdpbi10b3A6IC0yMHB4O1xyXG4gIG1hcmdpbi1ib3R0b206IDIwcHg7XHJcbiAgZGlzcGxheTogbm9uZTtcclxufVxyXG5cclxuLmFjdGl2ZSB7XHJcbiAgZGlzcGxheTogZmxleDtcclxufVxyXG5cclxuLmNvbXBsZXhpdHlfX2l0ZW0ge1xyXG4gIHdpZHRoOiAzMiU7XHJcbiAgaGVpZ2h0OiA2cHg7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogI0YyRjJGMjtcclxuICBib3JkZXItcmFkaXVzOiAycHg7XHJcbn1cclxuXHJcbi5jb21wbGV4aXR5X19pdGVtLS1iYWQge1xyXG4gIGJhY2tncm91bmQ6ICNGRjYzNTk7XHJcbn1cclxuXHJcbi5jb21wbGV4aXR5X19pdGVtLS1nb29kIHtcclxuICBiYWNrZ3JvdW5kOiAjRkZCOTY2O1xyXG59XHJcblxyXG4uY29tcGxleGl0eV9faXRlbS0tcGVyZmVjdCB7XHJcbiAgYmFja2dyb3VuZDogIzM4RUNBQztcclxufVxyXG5cclxuLm5nLXZhbGlkW3JlcXVpcmVkXSwgLm5nLXZhbGlkLnJlcXVpcmVkIHtcclxuICBib3JkZXItbGVmdDogNXB4IHNvbGlkICMzOEVDQUM7IC8qIGdyZWVuICovXHJcbn1cclxuXHJcbi5uZy1pbnZhbGlkOm5vdChmb3JtKSB7XHJcbiAgYm9yZGVyLWxlZnQ6IDVweCBzb2xpZCAjYTk0NDQyOyAvKiByZWQgKi9cclxufVxyXG4iXX0= */");

/***/ }),

/***/ "./src/app/register/register.component.ts":
/*!************************************************!*\
  !*** ./src/app/register/register.component.ts ***!
  \************************************************/
/*! exports provided: RegisterComponent, MustMatch */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterComponent", function() { return RegisterComponent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MustMatch", function() { return MustMatch; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");




var RegisterComponent = /** @class */ (function () {
    function RegisterComponent(httpClient, formBuilder) {
        this.httpClient = httpClient;
        this.formBuilder = formBuilder;
        this.submitted = false;
    }
    RegisterComponent.prototype.onSubmit = function () {
        //console.warn(this.registrForm.value);
        this.register();
    };
    RegisterComponent.prototype.register = function () {
        var user = {
            firstName: this.registerForm.get('firstName').value,
            lastName: this.registerForm.get('lastName').value,
            login: this.registerForm.get('login').value,
            email: this.registerForm.get('email').value,
            password: this.registerForm.get('password').value
        };
        this.httpClient.post("/api/v1/user/register/listener", user).subscribe();
    };
    Object.defineProperty(RegisterComponent.prototype, "form", {
        get: function () {
            return this.registerForm.controls;
        },
        enumerable: true,
        configurable: true
    });
    RegisterComponent.prototype.ngOnInit = function () {
        this.registerForm = this.formBuilder.group({
            firstName: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required],
            lastName: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required],
            login: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required],
            email: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].email]],
            password: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].minLength(8)]],
            confirmPassword: ['', _angular_forms__WEBPACK_IMPORTED_MODULE_3__["Validators"].required]
        }, {
            validator: MustMatch('password', 'confirmPassword')
        });
        // TODO: change to password-strength
        var pass = document.querySelector('.password');
        var complexityItem = document.getElementsByClassName('complexity__item');
        var complexity = document.querySelector('.complexity');
        var content = document.querySelector('.content');
        content.addEventListener('click', function (event) {
            // Toggle class "is-active"
            if (event.target === pass) {
                complexity.classList.add('active');
            }
            else {
                complexity.classList.remove('active');
            }
            pass.addEventListener('input', function (eve) {
                var lengthOfPass = eve.target.value.split('').length;
                if (lengthOfPass == 0) {
                    complexity.classList.remove('active');
                }
                else if (lengthOfPass <= 7) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--good');
                        complexityItem[i].classList.remove('complexity__item--perfect');
                    }
                    complexityItem[0].classList.add('complexity__item--bad');
                }
                else if (lengthOfPass <= 8 && eve.target.value.match(/[A-Z]/i)) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--bad');
                        complexityItem[i].classList.remove('complexity__item--perfect');
                    }
                    for (var i = 0; i < complexityItem.length - 1; i++) {
                        complexityItem[i].classList.add('complexity__item--good');
                    }
                }
                else if (lengthOfPass > 8 && eve.target.value.match(/[A-Z]/i) && eve.target.value.match(/\d/i) && eve.target.value.match(/[a-z]/i)) {
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.remove('complexity__item--bad');
                        complexityItem[i].classList.remove('complexity__item--good');
                    }
                    for (var i = 0; i < complexityItem.length; i++) {
                        complexityItem[i].classList.add('complexity__item--perfect');
                    }
                }
            });
        });
    };
    RegisterComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] },
        { type: _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormBuilder"] }
    ]; };
    RegisterComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-register',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./register.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/register/register.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./register.component.css */ "./src/app/register/register.component.css")).default]
        })
    ], RegisterComponent);
    return RegisterComponent;
}());

function MustMatch(controlName, matchingControlName) {
    return function (formGroup) {
        var control = formGroup.controls[controlName];
        var matchingControl = formGroup.controls[matchingControlName];
        if (matchingControl.errors && !matchingControl.errors.mustMatch) {
            // return if another validator has already found an error on the matchingControl
            return;
        }
        // set error on matchingControl if validation fails
        if (control.value !== matchingControl.value) {
            matchingControl.setErrors({ mustMatch: true });
        }
        else {
            matchingControl.setErrors(null);
        }
    };
}


/***/ }),

/***/ "./src/app/toolbar-menu/toolbar-menu.component.css":
/*!*********************************************************!*\
  !*** ./src/app/toolbar-menu/toolbar-menu.component.css ***!
  \*********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = (".filler {\r\n  flex: 1 1 auto;\r\n}\r\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvdG9vbGJhci1tZW51L3Rvb2xiYXItbWVudS5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsY0FBYztBQUNoQiIsImZpbGUiOiJzcmMvYXBwL3Rvb2xiYXItbWVudS90b29sYmFyLW1lbnUuY29tcG9uZW50LmNzcyIsInNvdXJjZXNDb250ZW50IjpbIi5maWxsZXIge1xyXG4gIGZsZXg6IDEgMSBhdXRvO1xyXG59XHJcbiJdfQ== */");

/***/ }),

/***/ "./src/app/toolbar-menu/toolbar-menu.component.ts":
/*!********************************************************!*\
  !*** ./src/app/toolbar-menu/toolbar-menu.component.ts ***!
  \********************************************************/
/*! exports provided: ToolbarMenuComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ToolbarMenuComponent", function() { return ToolbarMenuComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var ToolbarMenuComponent = /** @class */ (function () {
    function ToolbarMenuComponent() {
    }
    ToolbarMenuComponent.prototype.ngOnInit = function () {
    };
    ToolbarMenuComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-toolbar-menu',
            template: tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! raw-loader!./toolbar-menu.component.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/toolbar-menu/toolbar-menu.component.html")).default,
            styles: [tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"](__webpack_require__(/*! ./toolbar-menu.component.css */ "./src/app/toolbar-menu/toolbar-menu.component.css")).default]
        })
    ], ToolbarMenuComponent);
    return ToolbarMenuComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! hammerjs */ "./node_modules/hammerjs/hammer.js");
/* harmony import */ var hammerjs__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(hammerjs__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");






if (_environments_environment__WEBPACK_IMPORTED_MODULE_5__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_3__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_4__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! D:\netcraker\nc-autumn-2019\frontend\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map