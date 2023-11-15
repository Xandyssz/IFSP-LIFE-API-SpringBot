import {Component, Input, OnInit} from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ApiService } from "../api/api.service";

@Component({
  selector: 'app-pagina',
  templateUrl: 'modal-perfil.page.html',
  styleUrls: ['modal-perfil.page.scss'],
})

export class ModalPerfilPage implements OnInit {

  constructor(private modalController: ModalController, public apiService: ApiService) {}

  fecharModal() {
    this.modalController.dismiss();
  }

  ngOnInit() {
    const funcionarioLogado = this.apiService.funcionarioLogado;
    if (funcionarioLogado) {
    }
  }

}
