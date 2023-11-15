import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api/api.service";
import {MenuController, NavController, ModalController, ToastController} from '@ionic/angular';
import {ModalPerfilPage} from "../modal-perfil/modal-perfil.page";

@Component({
  selector: 'app-tab4',
  templateUrl: './tab4.page.html',
  styleUrls: ['./tab4.page.scss'],
})
export class Tab4Page implements OnInit {

  public funcionarios: any[] = [];
  public codigo : number = 0;
    constructor(private apiservice: ApiService,
                private menuController: MenuController,
                private modalController : ModalController,
                private toastController: ToastController,
                private navCtrl: NavController,) {
  }


  public getFuncionarios() {
    this.apiservice.getFuncionarios()
      .then(
        (dados:any[]) => {
          this.funcionarios = dados
        }
      )
      .catch(
        (erro) => {
          console.log(erro)
        }
      )
  }

  public getFuncionariosCodigo() {
      // Verifica se o código é menor que 0
      if (this.codigo <= 0) {
          this.exibirToast("O código inserido é inválido. Insira um código maior ou igual a zero.");
          return;
      }
    this.apiservice.getFuncionariosCodigo(this.codigo)
      .then(
        (dados:any[]) => {
          this.funcionarios = []
          this.funcionarios.push(dados)
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
