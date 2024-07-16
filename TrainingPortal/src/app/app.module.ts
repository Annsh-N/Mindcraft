import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MAT_RADIO_DEFAULT_OPTIONS, MatRadioModule } from '@angular/material/radio';
import { ModuleComponent } from './module/module.component';
import { QuestionOrderComponent } from './question-order/question-order.component';
import { QuestionMatchComponent } from './question-match/question-match.component';
import { QuestionMCQComponent } from './question-mcq/question-mcq.component';
import { ModuleEditorComponent } from './module-editor/module-editor.component';
import { QuillModule } from 'ngx-quill';
import { FormsModule } from '@angular/forms';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { NavbarComponent } from './navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { EditMcqComponent } from './edit-mcq/edit-mcq.component';
import { NewQuestionComponent } from './new-question/new-question.component';
import { QuestionComponent } from './question/question.component';
import { EditOrderComponent } from './edit-order/edit-order.component';
import { EditPanelComponent } from './edit-panel/edit-panel.component';
import { EditMatchComponent } from './edit-match/edit-match.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    UserHomeComponent,
    ModuleComponent,
    QuestionOrderComponent,
    QuestionMatchComponent,
    QuestionMCQComponent,
    ModuleEditorComponent,
    NavbarComponent,
    EditMcqComponent,
    NewQuestionComponent,
    QuestionComponent,
    EditOrderComponent,
    EditPanelComponent,
    EditMatchComponent,
    AdminHomeComponent,
    NavbarAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    QuillModule.forRoot(),
    FormsModule,
    BsDropdownModule.forRoot(),
    CommonModule,
    MatProgressBarModule,
  ],
  providers: [
    ModuleEditorComponent,
    {
      provide: MAT_RADIO_DEFAULT_OPTIONS,
      useValue: { color: 'warn' },
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
