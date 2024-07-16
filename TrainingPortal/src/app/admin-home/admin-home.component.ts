import { Component } from '@angular/core';
import { Module } from '../module/module.model';
import { User } from '../user-home/user.module';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
  modules : Array<Module> = new Array<Module>();
  users : Array<User> = new Array<User>();

  constructor() {
    for (let i = 0; i < 6; i++) {
      const module = new Module();
      module.title = "Module " + (i+1);
      this.modules.push(module);
    }

    for (let i = 0; i < 15; i++) {
      const user = new User();
      user.name = "User " + (i+1);
      user.progress = Math.round(Math.random()*100);
      this.users.push(user);
    }
  }
}
