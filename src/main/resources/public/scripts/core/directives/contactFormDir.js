(function(){

    angular.module('rest-contacts-book').directive('contactForm', dir);

    dir.$inject = [];

    function dir() {
        return {
            restrict: 'E',
            scope: {
                'resource': '=resource',
                'saveCb': '&saveCb',
                'cancelCb': '&cancelCb'
            },
            templateUrl: '/views/common/contactForm.html'
        };
    }

})();