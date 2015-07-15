(function(){

    angular.module('rest-contacts-book').controller('HomeCtl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'ContactResource'
        , '$log'
    ];

    function ctrl($scope, ContactResource, $log) {

        // ------------------- //

        function load(params) {
            ContactResource.page(params).then(function(data){
                $scope.contacts = data.items;
                $scope.pagingOptions.totalItems = data.total;
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

        // ------------------------- //

        $scope.pagingOptions = {
            currentPage: 1,
            totalItems: null,
            pageSize: 10
        };

        $scope.$watch('pagingOptions', function(newVal, oldVal) {
            if(newVal !== oldVal) {
                if(newVal.pageSize !== oldVal.pageSize || newVal.currentPage !== oldVal.currentPage) {
                    load({page: $scope.pagingOptions.currentPage-1, size: $scope.pagingOptions.pageSize});
                }
            }

        }, true);

        // ------------------------- //

        load();

    }

})();