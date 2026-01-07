/*
	React
	NodeJs 	SpringBoot
	  |			|
	React	  React
	----------------------MSA(JWT)

*/
const api = axios.create({
	baseURL: 'http://13.125.128.38:8080',
	timeout: 50000
})