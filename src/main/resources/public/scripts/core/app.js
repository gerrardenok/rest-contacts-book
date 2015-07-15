(function(){

    angular.module(
        'rest-contacts-book', [
            'ui.router'
            , 'ngResource'
            , 'ui.bootstrap'
        ]
    ).run(run);

    run.$inject = ['$rootScope'];

    function run($rootScope) {
        $rootScope.GLOBALS = {};
    }

})();
