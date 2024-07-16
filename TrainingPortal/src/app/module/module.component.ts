import { Component } from '@angular/core';
import { ModuleEditorComponent } from '../module-editor/module-editor.component';

@Component({
  selector: 'app-module',
  templateUrl: './module.component.html',
  styleUrls: ['./module.component.css']
})
export class ModuleComponent {
  progress: number = 30;
  answeredQuestions: number = 23;
  reattemptQuestions: number = 8;
  content : string = '';

  

  constructor(moduleEditor : ModuleEditorComponent) {
    this.content = moduleEditor.adminContent;
  }
}
