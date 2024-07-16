import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-question-order',
  templateUrl: './question-order.component.html',
  styleUrls: ['./question-order.component.css']
})
export class QuestionOrderComponent implements AfterViewInit, OnInit{

  questionText: string = '';
  options : string[] = [];
  shuffledOptions : string[] = [];
  userAnswers = new Array(4);

  constructor() {
    this.questionText = 'Order the steps to effectively implement Multi-Factor Authentication (MFA):';
    this.options = ['Evaluate MFA Solutions', 'Educate Users', 'Integrate with Existing Systems', 'Monitor and Maintain'];
  }

  ngOnInit(): void {
    this.shuffledOptions = this.options.slice().sort(() => Math.random() - 0.5);
  }
  
  
  isCorrect() {
    if (this.options.length !== this.userAnswers.length) {
      return false;
    }
    for (let i = 0; i < this.options.length; i++) {
      if (this.options[i] !== this.userAnswers[i]) {
        return false;
      }
    }
    return true;
  }

  ngAfterViewInit(): void {
    this.initializeDragAndDrop();
  }

  @ViewChildren('draggable') draggables: QueryList<ElementRef>;
  @ViewChildren('dropzone') dropzones: QueryList<ElementRef>;
  initializeDragAndDrop(): void {
    // const draggables = document.querySelectorAll('.draggable');
    // const dropzones = document.querySelectorAll('.dropzone');

    this.draggables.forEach(draggable => {
      draggable.nativeElement.addEventListener('dragstart', this.dragStart.bind(this));
      draggable.nativeElement.addEventListener('dragend', this.dragEnd.bind(this));
    });

    this.dropzones.forEach(dropzone => {
      dropzone.nativeElement.addEventListener('dragover', this.dragOver.bind(this));
      dropzone.nativeElement.addEventListener('drop', this.drop.bind(this));
    });
  }

  dragStart(event: DragEvent): void {
    const target = event.target as HTMLElement;
    event.dataTransfer?.setData('text/plain', target.dataset.step!);
    // setTimeout(() => {
    //   target.classList.add('disabled');
    // }, 0);
  }

  dragEnd(event: DragEvent): void {
    const draggable = event.target as HTMLElement;
    const dropzone = this.findDropzoneByStep(draggable.dataset.step!);
  }

  dragOver(event: DragEvent): void {
    event.preventDefault();
  }

  
  drop(event: DragEvent): void {
    event.preventDefault();
    const step = event.dataTransfer?.getData('text/plain');
    const draggable = this.findDraggableByStep(step!);
    if (event.target && event.target instanceof HTMLElement && event.target.classList.contains('dropzone') && event.target.innerText === "") {
      event.target.innerText = draggable?.textContent || '';
      draggable?.classList.add('disabled');
      event.target.classList.add('dropped');
    }
    console.log('Dropped:', draggable?.textContent, 'at', (event.target as HTMLElement).dataset.step);//remove later
    this.userAnswers[(event.target as HTMLElement).dataset.step] = draggable?.textContent;
    console.log(this.isCorrect());
    console.log(this.userAnswers);
  }

  findDraggableByStep(step: string): HTMLElement | null {
    const draggable = this.draggables.find(
      (draggable) => draggable.nativeElement.dataset.step === step
    );
    return draggable ? draggable.nativeElement : null;
  }

  findDropzoneByStep(step: string): HTMLElement | null {
    const dropzone = this.dropzones.find(
      (dropzone) => dropzone.nativeElement.dataset.step === step
    );
    return dropzone ? dropzone.nativeElement : null;
  }

  resetOrder(): void {
    this.draggables.forEach(draggable => {
      if (draggable.nativeElement.classList.contains('disabled')) {
        draggable.nativeElement.classList.remove('disabled');
      }
    });
    this.dropzones.forEach(dropzone => {
      if (dropzone.nativeElement.textContent !== "") {
        dropzone.nativeElement.textContent = '';
        dropzone.nativeElement.classList.remove('dropped');
      }
    });
    this.userAnswers = new Array(4);
    // Clear saved order from local storage if necessary
    // localStorage.removeItem('mfaOrder');
  }
}
