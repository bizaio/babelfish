import {
  AbstractControl,
  AbstractControlOptions,
  AsyncValidatorFn,
  FormGroup,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {FormControlType} from './babelfish-form-control.component';
import {BabelfishFormInput} from './controls/babelfish-form-control-input';

export class BabelfishFormGenerator {

  produceFormGroup(inputFields: any[]) {
    const formFields : {[key: string]: any} = {};

    inputFields.forEach(field => {
      if(field.fieldType == "INPUT") {
        formFields[field.attribute] = new BabelfishFormInput(field.defaultValue, field.tooltip, []);
        if(field.readOnly == "true") {
          formFields[field.attribute].disabled = true;
        }

      }
    });
  }

}
