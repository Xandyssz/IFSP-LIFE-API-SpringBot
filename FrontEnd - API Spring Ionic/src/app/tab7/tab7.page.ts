import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api/api.service";
import {MenuController, NavController, ModalController, ToastController} from '@ionic/angular';
import {ModalPerfilPage} from "../modal-perfil/modal-perfil.page";
@Component({
  selector: 'app-tab7',
  templateUrl: './tab7.page.html',
  styleUrls: ['./tab7.page.scss'],
})
export class Tab7Page implements OnInit {
  public vendas: any[] = [];
  public codigo : number = 0;


  constructor(private apiservice: ApiService,
              private menuController: MenuController,
              private modalController : ModalController,
              private toastController: ToastController,
              private navCtrl: NavController,) {
  }

  public getVendas() {
    this.apiservice.getVendas()
        .then(
            (dados: any[]) => {
              this.vendas = dados.map(venda => {
                // Inicialize as propriedades expandidas para cada tipo de detalhe
                venda.detalhes = {
                  expandidoDadosVenda: false,
                  expandidoDadosCaixa: false,
                  expandidoDadosProdutos: false,
                  expandidoDadosConvenio: false,
                };
                return venda;
              });
            }
        )
        .catch(
            (erro) => {
              console.log(erro)
            }
        )
  }

  public getVendasCodigo() {
    // Verifica se o código é menor que 0
    if (this.codigo <= 0) {
      this.exibirToast("O código inserido é inválido. Insira um código maior ou igual a zero.");
      return;
    }
    this.apiservice.getVendasCodigo(this.codigo)
        .then(
            (dados: any[]) => {
              this.vendas = dados.map(venda => {
                venda.detalhes = {
                  expandidoDadosVenda: false,
                  expandidoDadosCaixa: false,
                  expandidoDadosProdutos: false,
                  expandidoDadosConvenio: false,
                };
                return venda;
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

  public toggleVendas(venda: any) {
    venda.expandido = !venda.expandido;
  }

  public getIconName(venda: any) {
    return venda.expandido ? 'chevron-down-outline' : 'chevron-forward-outline';
  }

  public toggleDetalhes(tipo: string, venda: any) {
    switch (tipo) {
      case 'dadosVendas':
        venda.detalhes.expandidoDadosVenda = !venda.detalhes.expandidoDadosVenda;
        break;
      case 'dadosCaixa':
        venda.detalhes.expandidoDadosCaixa = !venda.detalhes.expandidoDadosCaixa;
        break;
      case 'dadosProdutos':
        venda.detalhes.expandidoDadosProdutos = !venda.detalhes.expandidoDadosProdutos;
        break;
      case 'dadosConvenios':
        venda.detalhes.expandidoDadosConvenio = !venda.detalhes.expandidoDadosConvenio;
        break;
      default:
        break;
    }
  }

  public getIconNameDetalhes(tipo: string, venda: any) {
    switch (tipo) {
      case 'dadosVendas':
        return venda.detalhes.expandidoDadosVenda ? 'chevron-down-outline' : 'chevron-forward-outline';
      case 'dadosCaixa':
        return venda.detalhes.expandidoDadosCaixa ? 'chevron-down-outline' : 'chevron-forward-outline';
      case 'dadosProdutos':
        return venda.detalhes.expandidoDadosProdutos ? 'chevron-down-outline' : 'chevron-forward-outline';
      case 'dadosConvenios':
        return venda.detalhes.expandidoDadosConvenio ? 'chevron-down-outline' : 'chevron-forward-outline';
      default:
        return 'chevron-forward-outline';
    }
  }

  ngOnInit() {
  }

}
