import {AbstractControl, AbstractControlOptions, AsyncValidatorFn, FormGroup, ValidatorFn} from '@angular/forms';
import {BabelfishFormInput} from './controls/babelfish-form-control-input';

export class BabelfishFormGroup extends FormGroup {

    private _submitted = false;

    hideAllControls() {
        Object.keys(this.controls).forEach((key) => (this.controls[key] as BabelfishFormInput).isVisible = false);
    }

    showControl(controlName: string): void {
        (this.controls[controlName] as BabelfishFormInput).isVisible = true;
    }

    get submitted() { return this._submitted; };

    setSubmitted(submitted) {
        this._submitted = submitted;
    }

}
