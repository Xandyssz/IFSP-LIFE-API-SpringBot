import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api/api.service";
import {MenuController, NavController, ModalController, ToastController} from '@ionic/angular';
import {ModalPerfilPage} from "../modal-perfil/modal-perfil.page";

@Component({
  selector: 'app-tab3',
  templateUrl: './tab3.page.html',
  styleUrls: ['./tab3.page.scss'],
})
export class Tab3Page implements OnInit {

  public fornecedores: any[] = [];
  public codigo : number = 0;
    constructor(private apiservice: ApiService,
                private menuController: MenuController,
                private modalController : ModalController,
                private toastController: ToastController,
                private navCtrl: NavController,) {
  }


  public getFornecedores() {
    this.apiservice.getFornecedores()
      .then(
        (dados:any[]) => {
          this.fornecedores = dados
        }
      )
      .catch(
        (erro) => {
          console.log(erro)
        }
      )
  }

  public getFornecedoresCodigo() {
      // Verifica se o código é menor que 0
      if (this.codigo <= 0) {
          this.exibirToast("O código inserido é inválido. Insira um código maior ou igual a zero.");
          return;
      }

    this.apiservice.getFornecedoresCodigo(this.codigo)
      .then(
        (dados:any[]) => {
          this.fornecedores = []
          this.fornecedores.push(dados)
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

    ngOnInit() {
  }

}
