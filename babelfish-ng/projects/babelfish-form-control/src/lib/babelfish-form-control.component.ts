import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';
import {BabelfishFormControl} from './controls/babelfish-form-control';
import {BabelfishFormInput} from './controls/babelfish-form-input';
import {BabelfishFormSelect} from './controls/babelfish-form-select';
import {BabelfishFormCheckbox} from './controls/babelfish-form-checkbox';
import {BabelfishFormDate} from './controls/babelfish-form-date';
import {BabelfishFormTextarea} from './controls/babelfish-form-textarea';
import {BabelfishFormDuration} from './controls/babelfish-form-duration';

type FormControlType = BabelfishFormInput & BabelfishFormSelect & BabelfishFormTextarea & BabelfishFormDate & BabelfishFormCheckbox & BabelfishFormDuration & AbstractControl & FormGroup;

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

    constructor() { }

    ngOnInit() {}

    hasRequiredValidator(abstractControl: AbstractControl): boolean {
        if (abstractControl.validator) {
            const validator = abstractControl.validator({}as AbstractControl);
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
                    case 'required': return `${control.label} is required field`;
                    case 'uuid': return `${control.label} must have UUID format`;
                    case 'SERVER': return control.errors[key];
                }
            }
        }

        return '';
    }

}
