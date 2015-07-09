(function(){

    angular.module(
        'rest-contacts-book', [
            'ui.router'
            , 'ngResource'
        ]
    ).run(run);

    run.$inject = ['$rootScope'];

    function run($rootScope) {
        $rootScope.GLOBALS = {};
    }

})();
