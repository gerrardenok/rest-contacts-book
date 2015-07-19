(function(){

    angular.module('rest-contacts-book').controller('ContactItemCtl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'ContactResource'
        , '$log'
    ];

    /**
     * @require $scope.contact
     * */

    function ctrl($scope, ContactResource, $log) {

        $scope.viewEdit = false;
        $scope.loading = false;
        $scope.backup = angular.copy($scope.contact) ;

        $scope.editContact = function(resource) {
            $scope.backup = angular.copy($scope.contact) ;
            $scope.viewEdit = true;
        };

        $scope.editSave = function() {
            $scope.loading = true;
            $scope.contact.$update(function(){
                $scope.backup = angular.copy($scope.contact);
                $scope.loading = false;
                $scope.viewEdit = false;
            });
        };

        $scope.editCancel = function() {
            $scope.contact = $scope.backup;
            $scope.viewEdit = false;
        };

    }

})();