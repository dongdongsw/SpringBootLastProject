/*
	React
	NodeJs 	SpringBoot
	  |			|
	React	  React
	----------------------MSA(JWT)

*/
const api = axios.create({
	baseURL: 'http://13.125.246.116:8080/',
	timeout: 50000
})