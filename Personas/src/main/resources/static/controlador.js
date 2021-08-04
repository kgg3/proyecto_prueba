var app = angular.module('buscadorPersonas', []);

app.controller('buscadorPersonasCtrl', ['$scope', '$http', function($scope, $http) {
	
	$scope.listaPersonas = [];
	$scope.literalBusqueda = '';
	$scope.filtros = [{
		'codigo':'NO', 
		'literal':'Nombre'
	},{
		'codigo':'AP', 
		'literal':'Apellido'
	},{
		'codigo':'ED', 
		'literal':'Edad'
	}];
	$scope.filtrosCampo = [{
		'codigo':'NO', 
		'literal':'Nombre'
	},{
		'codigo':'AP', 
		'literal':'Apellido'
	}];
	
	$scope.filtroOrden = 'NO';
	$scope.filtroCampo = 'NO';
	
	$scope.buscarPersonas = function(literalBusqueda){
		$scope.literalError = '';
		var url = '/personas?literalBusqueda=' + literalBusqueda + '&filtro=' + $scope.filtroOrden + '&filtroCampo=' + $scope.filtroCampo;
		$http.get(url).then(function successCallback(personas) {
		    $scope.listaPersonas = personas.data;
		  }, function errorCallback(error) {
		    $scope.literalError = error;
		  });
	}
	
	$scope.cambioOrden = function(filtroOrden){
		$scope.literalError = '';
		var url = '/ordenar';
		var parametros = {
			filtro: filtroOrden,
			listaPersona: $scope.listaPersonas
		};
		$http.post(url, parametros).then(function successCallback(personas) {
		    $scope.listaPersonas = personas.data;
		  }, function errorCallback(error) {
		    $scope.literalError = error;
		  });
	}
	
	function init(){
		$scope.buscarPersonas('');
	}
	
	init();
}]);