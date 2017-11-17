

var awardApp = angular.module('awardApp', []);

awardApp.controller('allAwards', function($scope, $http) {
    
	$http.get('/awards/all').
    then(function(response) {
        $scope.allAwards = response.data;
    });
    
    
    $scope.selectedAwards = [];
    
    $scope.bang = function() {
    	alert("BANG!");
    }
    
    $scope.reset = function() {
    	$scope.combinedImage = null;
    	$scope.selectedAwards.length = 0
    }
    
    $scope.submitSelectedAwards = function() {
    	
    	$http({
    		method : "POST",
    		url : "/awards/submitSelectedAwards",
    		data : $scope.selectedAwards
    	}).success(function(data, status, headers, config) {
    		$scope.imageText = data.text;
    		$scope.combinedImage = data.image;
    	});
    }
});


/* For reference only


reportApp.controller('allBlocked', function($scope, $http) {
    $http.get('/report/blocked').
        then(function(response) {
            $scope.allBlocked = response.data;
        });
});

reportApp.controller('blockedById', function($scope, $http) {
	$http({
		method : "GET",
		url : "/report/blockedById",
		params : {id:6}
	}).
        then(function(response) {
            $scope.blockedById = response.data;
        });
});

reportApp.controller('searchById', ['$scope', '$http', function ($scope, $http) {
    $scope.enteredId = "";
    $scope.results = [];

    $scope.search = function () {
    	$http({
    		method : "GET",
    		url : "/report/blockedById",
    		params : {id:$scope.enteredId}
    	})
          .then(function (response) { $scope.blockedById = response.data; })
          .then(function (failure) { console.log("failed :(", failure); });
    }
}]);

*/