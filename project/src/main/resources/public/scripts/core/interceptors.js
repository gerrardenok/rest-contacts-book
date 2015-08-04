(function(){

    angular.module('rest-contacts-book')
        .factory('httpRequestInterceptor', srv)
        .config(cfg);

    cfg.$inject = [
        '$httpProvider'
    ];

    function cfg($httpProvider){
        $httpProvider.interceptors.push('httpRequestInterceptor');
    }

    srv.$inject = ['$q', '$window', '$log'];

    function srv($q, $window, $log) {
        return {
            'responseError': function(rejection) {
                if(rejection.data && rejection.data.exception){
                    switch (rejection.data.exception) {
                        default:
                            $log.warn('httpRequestInterceptor#responseError: default', rejection);
                            break;
                    }
                }
                return $q.reject(rejection);
            }
        };
    }


})();
