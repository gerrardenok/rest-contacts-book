(function(){

    angular.module('rest-contacts-book').controller('HomeCtl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'ContactResource'
        , '$log'
    ];

    function ctrl($scope, ContactResource, $log) {

        ContactResource.page().then(function(response){
            $scope.contacts = response.data.content;
        })

    }

})();