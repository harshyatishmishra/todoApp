/*$(function() {

	alert("Lord Krishna");


});
*/
"use strict"
var todoModule = angular.module("todo", [ ]);
todoModule.controller("postcontroller", function($scope, crudService, LocalData) {
	var formData = {}
	$scope.data = formData;
	$scope.errorVO = {
		"hasError" : false,
		"errorDesc" : ""
	};
	$scope.save = function() {
		crudService.save($scope.data, successCallback, errorCallback);
	}
	function successCallback(response) {
		LocalData.data.push(response.data);
		$scope.errorVO["hasError"] = false;
		$scope.errorVO["errorDesc"] = "";
	}
	function errorCallback(response) {
		$scope.errorVO["hasError"] = true;
		$scope.errorVO["errorDesc"] = response.data.message;
	}

});
todoModule.controller("dataMgmtController",dataMgmtController);
function dataMgmtController($scope, LocalData, crudService) {
	$scope.selectedData={};
	$scope.data = LocalData.data;

	$scope.errorVO = {
		"hasError" : false,
		"errorDesc" : ""
	};
	$scope.fetchAllData = function() {
		crudService.listAll(function(response) {
			Array.prototype.push.apply(LocalData.data, response.data);
			successCallback(response)
		}, errorCallback);
	}
	$scope.deleteRecord = function(id, index) {
	
		crudService.deleteRecord(id, function() {
			$scope.data.splice(index, 1);
		}, errorCallback)
	}
	$scope.edit=function(todo){
		$scope.selectedData=angular.copy(todo);

	}
	$scope.save=function(index){
		crudService.updateRecord(angular.copy($scope.selectedData),function(response){
			successCallback(response);
			$scope.data[index]=response.data;
			$scope.cancel();
			
		},function(res){errorCallback(res);if(angular.isUndefined(res.data.message))$scope.errorVO["errorDesc"]="Data may be deleted"});
		
	}

	function successCallback(response) {
		$scope.errorVO={}
	}
	function errorCallback(response) {
		$scope.errorVO["hasError"] = true;
		$scope.errorVO["errorDesc"] = response.data.message;
	}
	
	$scope.getTemplate=function(todo){
	if(todo.id===$scope.selectedData.id) return "editTemplate";
	else return "showTemplate";
}
	$scope.cancel=function(){
		$scope.selectedData={};
		$scope.errorVO={}
	}

};



dataMgmtController.$inject = ['$scope', 'LocalData', 'crudService'];


todoModule.service("crudService", function($http, ENDPOINT) {
	this.save = function(data, successCallback, errorCallback) {
		return $http.post(ENDPOINT.SAVE, data).then(successCallback, errorCallback);

	}
	this.listAll = function(successCallback, errorCallback) {
		return $http.get(ENDPOINT.LIST).then(successCallback, errorCallback);

	}
	this.deleteRecord = function(id, successCallback, errorCallback) {
		return $http.delete(ENDPOINT.DELETE.replace("<id>", id)).then(successCallback, errorCallback);
	}
	this.updateRecord = function(todo, successCallback, errorCallback) {
		return $http.put(ENDPOINT.UPDATE.replace("<id>", todo.id),todo).then(successCallback, errorCallback);
	}

})


todoModule.provider('LocalData', function() {
	this.data = []
	this.$get = function() {
		return {
			data : this.data
		}
	}
});
todoModule.constant("ENDPOINT", {
	"SAVE" : "../api/todos",
	"LIST" : "../api/todos",
	"DELETE" : "../api/todos/<id>",
	"UPDATE" : "../api/todos/<id>"
});