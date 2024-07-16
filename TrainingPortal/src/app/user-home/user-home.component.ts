import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  progress: number = 75;
  answeredQuestions: number = 23;
  reattemptQuestions: number = 8;

  completedModules: number = 3;
  modules = new Array(5);
}
