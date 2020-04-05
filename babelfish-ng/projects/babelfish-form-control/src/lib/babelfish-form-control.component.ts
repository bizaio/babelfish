import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormGroup} from '@angular/forms';
import {BabelfishFormControl} from './controls/babelfish-form-control';
import {BabelfishFormSelect} from './controls/babelfish-form-control-select';
import {BabelfishFormTextarea} from './controls/babelfish-form-control-textarea';
import {BabelfishFormInput} from './controls/babelfish-form-control-input';
import {BabelfishFormCheckbox} from './controls/babelfish-form-control-checkbox';
import {BabelfishFormDate} from './controls/babelfish-form-control-date';
import {BabelfishFormDuration} from './controls/babelfish-form-control-duration';
import {ResponseGetTypes, TypeService} from '@bizaoss/babelfish-type-service-client';
import {BabelfishFormSelectType} from './controls/babelfish-form-control-selecttype';

export enum BabelfishFormControlTypes {
  INPUT = 'input',
  SELECT = 'select',
  TEXTAREA = 'textarea',
  DATE = 'date',
  CHECKBOX = 'checkbox',
  DURATION = 'duration',
  SELECTTYPE = 'selecttype',
}

type FormControlType =
  BabelfishFormInput
  & BabelfishFormSelect
  & BabelfishFormSelectType
  & BabelfishFormTextarea
  & BabelfishFormDate
  & BabelfishFormCheckbox
  & BabelfishFormDuration
  & AbstractControl
  & FormGroup;

@Component({
  selector: 'babelfish-form-control',
  templateUrl: './babelfish-form-control.component.html',
  styleUrls: ['./babelfish-form-control.component.scss']
})
export class BabelfishFormControlComponent implements OnInit {

  @Input()
  public control: FormControlType;

  @Input()
  public showErrors = false;

  @Input()
  public showLabel = true;
  private loading = false;
  public idForLabel = `${Date.now()}${Math.round(Math.random() * 10000)}`;

  public originalOrder = ((): number => 0);
  private typeMap: ResponseGetTypes = {};

  constructor(private typeService: TypeService) {

  }

  ngOnInit() {

  }

  public populateTypes(typeNames: string[], overwrite: boolean = false) {
    // Strip out types we have already retrieved
    if (!overwrite) {
      typeNames = typeNames.filter((typeName) => !this.typeMap.fieldTypes[typeName]);
    }

    if (typeNames.length > 0) {
      this.loading = true;

      this.typeService.getEnumerationTypes(typeNames).subscribe(returnedTypes => {
        for (const oneType of typeNames) {
          this.typeMap.fieldTypes[oneType] = returnedTypes.fieldTypes[oneType];
        }
        this.loading = false;
      });
    }
  }

  hasRequiredValidator(abstractControl: AbstractControl): boolean {
    if (abstractControl.validator) {
      const validator = abstractControl.validator({} as AbstractControl);
      if (validator && validator.required) {
        return true;
      }
    }
    return false;
  }

  getFirstError(control: BabelfishFormControl): string {
    if (control && control.errors) {
      const errors = Object.keys(control.errors);

      for (const key of errors) {
        switch (key) {
          case 'required':
            return `${control.label} is required field`;
          case 'uuid':
            return `${control.label} must have UUID format`;
          case 'SERVER':
            return control.errors[key];
        }
      }
    }

    return '';
  }

}
