import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Filter} from "../models/filter.model";


@Component({
  selector: 'app-filter-dialog',
  templateUrl: './filter-dialog.component.html',
  styleUrls: ['./filter-dialog.component.scss']
})
export class FilterDialogComponent {

  title: string;

  constructor(
    public dialogRef: MatDialogRef<FilterDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
