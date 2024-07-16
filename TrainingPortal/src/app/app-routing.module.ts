import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModuleComponent } from './module/module.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { ModuleEditorComponent } from './module-editor/module-editor.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';

const routes: Routes = [
  {path: 'module', component:ModuleComponent},
  {path: 'home', component:UserHomeComponent},
  {path: 'module-editor', component:ModuleEditorComponent},
  {path: 'admin-home', component:AdminHomeComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
