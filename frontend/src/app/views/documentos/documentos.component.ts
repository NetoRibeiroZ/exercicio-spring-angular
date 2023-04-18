import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

import {DadosTransferencia, Documento, Pasta, Setor} from "../../models";
import {ApiService} from "../../api/api.service";
import {
  DocumentoDialogComponent
} from "../../shared/documento-dialog/documento-dialog.component";
import {
  TransferirDialogComponent
} from "../../shared/transferir-dialog/transferir-dialog.component";
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.sass']
})
export class DocumentosComponent implements OnInit {
  filtroNome = new FormControl('');
  pasta: Pasta = {nome: "Carregando..."};
  documentos: Documento[] = [];
  setorId: number = 0;
  pastaId: number = 0;
  documentosFiltrados:Documento[] = [];

  constructor(
    private route: ActivatedRoute,
    private api: ApiService,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.listar();
  }

  listar(): void {
    this.route.paramMap.subscribe(async next => {
      this.setorId = parseInt(<string>next.get("setorId"));
      this.pastaId = parseInt(<string>next.get("pastaId"));
      this.pasta = await this.api.getPasta(this.setorId, this.pastaId);
      this.documentos = await this.api.getDocumentos(this.setorId, this.pastaId);
      this.documentosFiltrados = this.documentos;
    });
  }

  salvar(doc: Documento = {titulo: "Novo documento"}): void {
    this.dialog.open(DocumentoDialogComponent, {
      data: ({...doc}),
      width: "50%"
    }).afterClosed().subscribe(async result => {
      if (result) {
        await this.api.salvarDocumento(this.setorId, this.pastaId, result);
        this.listar();
      }
    });
  }
  exibirHistorico(doc: Documento): void {
    this.dialog.open(DocumentoDialogComponent, {

    }).afterClosed().subscribe(async result => {
      if (result) {
        this.listar();
      }
    });
  }
  transferir(doc: Documento) {
    this.dialog.open(TransferirDialogComponent, {
      data: ({...doc}),
      width: "50%"
    }).afterClosed().subscribe(async (result: DadosTransferencia) => {
      if (result) {
        const {setor, pasta, documento} = result;
        await this.api.salvarDocumento(<number>setor.id, <number>pasta.id, documento);
        this.listar();
      }
    });
  }
filtrarDocumentos(event:any) {
  this.documentosFiltrados = this.documentos.filter(documento =>
        documento.titulo.toLowerCase().includes(event.target.value.toLowerCase())
      );

  }
}
