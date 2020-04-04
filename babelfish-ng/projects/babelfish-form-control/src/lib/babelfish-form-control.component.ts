import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup} from '@angular/forms';
import {SelectItem} from 'primeng/api';

export enum BabelfishFormControlTypes {
  INPUT = 'input',
  SELECT = 'select',
  TEXTAREA = 'textarea',
  DATE = 'date',
  CHECKBOX = 'checkbox',
  DURATION = 'duration',
}

export class BabelfishFormControl extends FormControl {

  type: BabelfishFormControlTypes;
  label: string;
  isVisible = true;

  constructor(type: BabelfishFormControlTypes, defaultValue, label: string, validators = [], asyncValidators = []) {
    super(defaultValue, validators, asyncValidators);

    this.type = type;
    this.label = label;
  }
}

export class BabelfishFormInput extends BabelfishFormControl {

  subType: string;

  constructor(defaultValue, label: string, validators = [], subType = 'text', asyncValidators = []) {
    super(BabelfishFormControlTypes.INPUT, defaultValue, label, validators, asyncValidators);

    this.subType = subType;
  }
}

export class BabelfishFormSelect extends BabelfishFormControl {

  options: SelectItem[];

  constructor(defaultValue, label: string, validators = [], options: SelectItem[] = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.SELECT, defaultValue, label, validators, asyncValidators);

    this.options = options;
  }
}

export class BabelfishFormTextarea extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.TEXTAREA, defaultValue, label, validators, asyncValidators);
  }
}

export class BabelfishFormDate extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.DATE, defaultValue, label, validators, asyncValidators);
  }
}

export class BabelfishFormCheckbox extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.CHECKBOX, defaultValue, label, validators, asyncValidators);
  }
}

export class BabelfishFormDuration extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.DURATION, defaultValue, label, validators, asyncValidators);
  }
}

type FormControlType =
  BabelfishFormInput
  & BabelfishFormSelect
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

  public idForLabel = `${Date.now()}${Math.round(Math.random() * 10000)}`;

  public originalOrder = ((): number => 0);

  constructor() {
  }

  ngOnInit() {
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
