(function(){

    angular.module('rest-contacts-book').controller('ContactListCtl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'ContactResource'
        , '$log'
        , '$modal'
    ];

    function ctrl($scope, ContactResource, $log, $modal) {

        // ------------------- //

        $scope.listLoading = false;
        $scope.pagingOptions = {
            currentPage: 1,
            totalItems: null,
            pageSize: 10
        };
        $scope.filter = null;

        // ------------------- //

        function load(params) {
            $scope.listLoading = true;
            ContactResource.page(params).then(function(data){
                $scope.contacts = data.items;
                $scope.pagingOptions.totalItems = data.total;
                $scope.listLoading = false;
            })
        }

        function getLoadOptions(){
            return {
                page: $scope.pagingOptions.currentPage-1
                , size: $scope.pagingOptions.pageSize
                , filter: ($scope.filter) ? "%" + $scope.filter + "%" : null
            };
        }

        $scope.deleteContact = function($resourceItem){
            $modal.open({
                templateUrl: '/views/common/confirmModal.html'
            }).result.then(
                function(){
                    $scope.listLoading = true;
                    $resourceItem.$delete(function(){
                        load();
                    });
                }
            );
        };

        // ----------------------------- //

        $scope.concatToSave = null;

        $scope.addContact = function(resource) {
            $scope.concatToSave = new ContactResource({});
        };

        $scope.addSave = function(resource) {
            $scope.listLoading = true;
            resource.$save(function(){
                $scope.concatToSave = null;
                load();
            });
        };

        $scope.addCancel = function(resource) {
            $scope.concatToSave = null;
        };

        $scope.pageChanged = function() {
            load(getLoadOptions());
        };

        // ------------------------- //

        $scope.changeFilter = function(){
            $scope.pagingOptions.currentPage = 1;
            load(getLoadOptions());
        };

        // ------------------------- //

        load();

    }

})();