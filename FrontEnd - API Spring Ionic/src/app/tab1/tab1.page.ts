import { Component, OnInit, Renderer2 } from '@angular/core';
import {IonTabs, NavController} from '@ionic/angular';
import { ViewChild } from '@angular/core';
import {ApiService} from "../api/api.service";
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-tab1',
  templateUrl: './tab1.page.html',
  styleUrls: ['./tab1.page.scss'],
})
export class Tab1Page implements OnInit {

  loginData = {
    login: '',
    senha: ''
  };

  constructor(
    private renderer: Renderer2,
    private apiService: ApiService,
    private navCtrl: NavController,
    private toastController: ToastController
  ) { }

  ionViewDidEnter() {
    const tabbar = document.querySelector('ion-tab-bar');
    this.renderer.setStyle(tabbar, 'display', 'none');
  }

  ionViewWillLeave() {
    const tabbar = document.querySelector('ion-tab-bar');
    this.renderer.removeStyle(tabbar, 'display');
  }

  ngOnInit() {
  }

  async fazerLogin() {
    try {
      const response: any = await this.apiService.fazerLogin(this.loginData);

      if (response) {
        // Atualiza a navegação para Tab2Page
        this.navCtrl.navigateForward('/tabs/tab6').then(() => {
          // Carrega as informações do funcionário logado
          this.apiService.getFuncionariosCodigo(response.codigo_funcionario);
        });
      } else {
        console.error('Login falhou');
        this.exibirToast('ERRO: Login ou Senha inseridos incorretamente');
      }
    } catch (error) {
      console.error('Erro durante o login', error);
      this.exibirToast('ERRO NO BACK-END');
    }
  }

  async exibirToast(mensagem: string) {
    const toast = await this.toastController.create({
      message: mensagem,
      duration: 3000,  // Duração em milissegundos
      position: 'top'  // Posição do toast: 'top', 'bottom' ou 'middle'
    });
    toast.present();
  }



}
