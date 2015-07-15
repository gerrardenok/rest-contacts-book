(function(){

    angular.module('rest-contacts-book').controller('HomeCtl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'ContactResource'
        , '$log'
    ];

    function ctrl($scope, ContactResource, $log) {

        load();

        // ------------------- //

        function load() {
            ContactResource.page().then(function(data){
                $scope.contacts = data;
            })
        }

        $scope.deleteContact = function($resourceItem){
            $resourceItem.$delete(function(){
                load();
            });
        };

        // ----------------------------- //

        $scope.concatToSave = null;

        $scope.addContact = function(resource) {
            $scope.concatToSave = new ContactResource({});
        };

        $scope.addSave = function(resource) {
            resource.$save(function(){
                $scope.concatToSave = null;
                load();
            });
        };

        $scope.addCancel = function(resource) {
            $scope.concatToSave = null;
        };

        // ----------------------------- //

        $scope.editContact = function(resource) {
            resource.viewEdit = true;
        };

        $scope.editSave = function(resource) {
            resource.$update(function(){
                resource.viewEdit = false;
            });
        };

        $scope.editCancel = function(resource) {
            resource.viewEdit = false;
        };

    }

})();