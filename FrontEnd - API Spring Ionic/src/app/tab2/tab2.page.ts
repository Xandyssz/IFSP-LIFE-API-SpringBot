import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api/api.service";
import { MenuController, NavController, ModalController } from '@ionic/angular';
import {ModalPerfilPage} from "../modal-perfil/modal-perfil.page";
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-tab2',
  templateUrl: './tab2.page.html',
  styleUrls: ['./tab2.page.scss'],
})
export class Tab2Page implements OnInit {

  public convenios: any[] = [];
  public codigo : number = 0;
  constructor(private apiservice: ApiService,
              private menuController: MenuController,
              private modalController : ModalController,
              private toastController: ToastController,
              private navCtrl: NavController,) {
  }

  public getConvenios() {
    this.apiservice.getConvenios()
      .then(
        (dados:any[]) => {
          this.convenios = dados
        }
      )
      .catch(
        (erro) => {
          console.log(erro)
        }
      )
  }

  public getConveniosCodigo() {
    // Verifica se o código é menor que 0
    if (this.codigo <= 0) {
      this.exibirToast("O código inserido é inválido. Insira um código maior ou igual a zero.");
      return;
    }

    this.apiservice.getConveniosCodigo(this.codigo)
        .then(
            (dados: any[]) => {
              this.convenios = []
              this.convenios.push(dados)
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
