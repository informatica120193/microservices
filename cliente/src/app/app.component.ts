import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../environments/environment';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
	})
	export class AppComponent {
	title = 'cliente';

	txtcaja1 = '';
	txtcaja2 = '0';
	valor1;
	valor2;
	unum;
	borrar: boolean;
	urlServicio = `http://0.0.0.0:8090`;


	constructor(private http: HttpClient) { }


	escribir(n){
		let caja2 = this.txtcaja2;
		if (this.borrar) {
		//	this.txtcaja1= "";
			this.borrar = false;
			this.txtcaja2 = n;
		} else if (caja2 == "0" && n != "."){
			caja2 = caja2.replace("0", "")
			this.txtcaja2 = caja2 + n;
		} else {
			this.txtcaja2 = caja2 + n;
		}
	}

	arit(o) {
		const caja1 = this.txtcaja1;
		const caja2 = this.txtcaja2;
		console.log('caja1', this.txtcaja1);
		console.log('caja2', this.txtcaja2);
		this.unum = caja1.substring(caja1.length - 1);
		console.log('this.unum', this.unum);
		this.calcular();
		if (this.unum == "+" || this.unum == "-" || this.unum=="*" || this.unum=="/") {
			this.unum = this.unum.replace(this.unum,o);
			let res = caja1.substring(0,caja1.length-1);
			console.log('res', res);
			this.txtcaja1 = res+this.unum;
		}
		console.log('caja1 == ',  caja1 == '' && caja2 != '');
		if (caja1 == '' && caja2 != ''){
			this.txtcaja1 = caja2 + o;
		} else {
			this.txtcaja1 = caja1 + caja2 + o;
		}
		this.borrar = true;
	}

	calcular() {
		console.log('txtcaja1', this.txtcaja1);
		console.log('txtcaja2', this.txtcaja2);
		this.unum = this.txtcaja1.substring(this.txtcaja1.length - 1);
		this.evaluar();
		this.txtcaja1 = '';
		this.borrar = true;
		this.valor1 = undefined;
		this.valor2 =  undefined;
	}

	evaluar() {

		if (this.txtcaja1 == '' && this.txtcaja2 != ''){
			return;
		}
		let oper ;
		this.valor1 = this.valor1 ? this.valor1 : this.txtcaja1.substring(0,this.txtcaja1.length-1);
		this.valor2 = this.txtcaja2;
		console.log('valor1', this.valor1);
		console.log('valor2', this.valor2);
		console.log('this.unum', this.unum);

		switch (this.unum) {
			case '+':
				oper = 's';
				break;
			case '-':
				oper = 'r';
				break;
			case '*':
				oper = 'm';
				break;
			case '/':
				oper = 'd';
				break;
			default:
				break;
		}
		const url = `${this.urlServicio}/operacion?operacion=${oper}&numero1=${this.valor1}&numero2=${this.valor2}`;
		console.log('URL :' , url);
		this.http.get(url).subscribe((data: string) => {
			this.txtcaja2 = data;
			this.valor1 = data;
		});

		this.valor2 = '';
	}

	limpiar(){
		this.txtcaja1 = '';
		this.txtcaja2 = '0';
		this.valor1 = undefined;
		this.valor2 =  undefined;
	}
}
