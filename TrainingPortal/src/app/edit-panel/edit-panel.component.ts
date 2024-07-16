import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-edit-panel',
  templateUrl: './edit-panel.component.html',
  styleUrls: ['./edit-panel.component.css']
})
export class EditPanelComponent {
  @Input() editMode: boolean = false;
  @Input() changeType : string = 'MCQ';

  @Input() deleteQuestion: () => void = () => {};

  @Input() setChangeType(changeType: string) {
    this.changeType = changeType;
  }

  edit() {
    this.editMode = true;
  }
}
