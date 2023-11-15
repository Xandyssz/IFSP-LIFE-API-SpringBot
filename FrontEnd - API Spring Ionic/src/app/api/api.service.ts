import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  public funcionarioLogado: any;

  constructor(private http: HttpClient ) {  }

  // API PARA CONVÊNIOS
  public getConvenios() : Promise<any> {
    return this.http.get("http://localhost:8080/convenios/").toPromise()
  }
  public getConveniosCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/convenios/"+codigo).toPromise()
  }
  // ------------------------------------------------------------------------------------------------------------------------

  // API PARA FORNECEDOR
  public getFornecedores() : Promise<any> {
    return this.http.get("http://localhost:8080/fornecedores/").toPromise()
  }
  public getFornecedoresCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/fornecedores/"+codigo).toPromise()
  }
  // ------------------------------------------------------------------------------------------------------------------------

  // API PARA FUNCIONARIOS
  public getFuncionarios() : Promise<any> {
    return this.http.get("http://localhost:8080/funcionarios/").toPromise()
  }
  public getFuncionariosCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/funcionarios/"+codigo).toPromise()
  }
  // ------------------------------------------------------------------------------------------------------------------------

  // API PARA PRODUTOS
  public getProdutos() : Promise<any> {
    return this.http.get("http://localhost:8080/produto/").toPromise()
  }
  public getProdutoCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/produto/"+codigo).toPromise()
  }
  // ------------------------------------------------------------------------------------------------------------------------

  // API PARA COMPRAS
  public getCompras() : Promise<any> {
    return this.http.get("http://localhost:8080/compras/").toPromise()
  }
  public getCompraCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/compras/"+codigo).toPromise()
  }

  // ------------------------------------------------------------------------------------------------------------------------

  // API PARA VENDAS
  public getVendas() : Promise<any> {
    return this.http.get("http://localhost:8080/vendas/").toPromise()
  }
  public getVendasCodigo(codigo: number) : Promise<any> {
    return this.http.get("http://localhost:8080/vendas/"+codigo).toPromise()
  }

  // ------------------------------------------------------------------------------------------------------------------------


  public fazerLogin(loginData: any): Promise<any> {
    return this.http.post("http://localhost:8080/funcionarios/tabs/tabs1/login", loginData)
        .toPromise()
        .then((response: any) => {
          console.log('Resposta do serviço:', response);

          if (response) {
            this.funcionarioLogado = response;
            return response;
          } else {
            console.error('Login falhou');
            return null;
          }
        })
        .catch((error: any) => {
          console.error('Erro durante o login', error);
          return null;
        });
  }

}
