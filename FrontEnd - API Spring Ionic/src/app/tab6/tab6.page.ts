import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api/api.service";
import {MenuController, NavController, ModalController, ToastController} from '@ionic/angular';
import {ModalPerfilPage} from "../modal-perfil/modal-perfil.page";
@Component({
  selector: 'app-tab6',
  templateUrl: './tab6.page.html',
  styleUrls: ['./tab6.page.scss'],
})
export class Tab6Page implements OnInit {
  public compras: any[] = [];
  public codigo : number = 0;


  constructor(private apiservice: ApiService,
              private menuController: MenuController,
              private modalController : ModalController,
              private toastController: ToastController,
              private navCtrl: NavController,) {
  }

    public getCompras() {
        this.apiservice.getCompras()
            .then(
                (dados: any[]) => {
                    this.compras = dados.map(compra => {
                        // Inicialize as propriedades expandidas para cada tipo de detalhe
                        compra.detalhes = {
                            expandidoDadosCompra: false,
                            expandidoDadosProdutos: false,
                            expandidoDadosPagamento: false,
                            expandidoDadosFornecedores: false,
                        };
                        return compra;
                    });
                }
            )
            .catch(
                (erro) => {
                    console.log(erro)
                }
            )
    }

    public getCompraCodigo() {
        // Verifica se o código é menor que 0
        if (this.codigo <= 0) {
            this.exibirToast("O código inserido é inválido. Insira um código maior ou igual a zero.");
            return;
        }
        this.apiservice.getCompraCodigo(this.codigo)
            .then(
                (dados: any[]) => {
                    this.compras = dados.map(compra => {
                        compra.detalhes = {
                            expandidoDadosCompra: false,
                            expandidoDadosProdutos: false,
                            expandidoDadosPagamento: false,
                            expandidoDadosFornecedores: false,
                        };
                        return compra;
                    });
                }
            )
            .catch(
                (erro) => {
                    console.log(erro)
                }
            )
    }



  // Método para fechar o menu
  public sair() {
    this.navCtrl.navigateForward('/tab1');
  }

  async abrirModalMeuPerfil() {
    const modal = await this.modalController.create({
      component: ModalPerfilPage,
      cssClass: 'modal-perfil-modal',
    });

    return await modal.present();
  }

  async exibirToast(mensagem: string) {
    const toast = await this.toastController.create({
      message: mensagem,
      duration: 3000,  // Duração em milissegundos
      position: 'top'  // Posição do toast: 'top', 'bottom' ou 'middle'
    });
    toast.present();
  }

  public toggleCompra(compra: any) {
    compra.expandido = !compra.expandido;
  }

  public getIconName(compra: any) {
    return compra.expandido ? 'chevron-down-outline' : 'chevron-forward-outline';
  }

    public toggleDetalhes(tipo: string, compra: any) {
        switch (tipo) {
            case 'dadosCompra':
                compra.detalhes.expandidoDadosCompra = !compra.detalhes.expandidoDadosCompra;
                break;
            case 'dadosProdutos':
                compra.detalhes.expandidoDadosProdutos = !compra.detalhes.expandidoDadosProdutos;
                break;
            case 'dadosPagamento':
                compra.detalhes.expandidoDadosPagamento = !compra.detalhes.expandidoDadosPagamento;
                break;
            case 'dadosFornecedores':
                compra.detalhes.expandidoDadosFornecedores = !compra.detalhes.expandidoDadosFornecedores;
                break;
            default:
                break;
        }
    }

    public getIconNameDetalhes(tipo: string, compra: any) {
        switch (tipo) {
            case 'dadosCompra':
                return compra.detalhes.expandidoDadosCompra ? 'chevron-down-outline' : 'chevron-forward-outline';
            case 'dadosProdutos':
                return compra.detalhes.expandidoDadosProdutos ? 'chevron-down-outline' : 'chevron-forward-outline';
            case 'dadosPagamento':
                return compra.detalhes.expandidoDadosPagamento ? 'chevron-down-outline' : 'chevron-forward-outline';
            case 'dadosFornecedores':
                return compra.detalhes.expandidoDadosFornecedores ? 'chevron-down-outline' : 'chevron-forward-outline';
            default:
                return 'chevron-forward-outline';
        }
    }

  ngOnInit() {
  }

}
