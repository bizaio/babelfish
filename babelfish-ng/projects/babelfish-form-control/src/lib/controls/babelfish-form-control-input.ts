import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormInput extends BabelfishFormControl {

  subType: string;

  constructor(defaultValue, label: string, tooltip: string, validators = [], controlStyle: string = "", subType: string = "text", asyncValidators = []) {
    super(BabelfishFormControlTypes.INPUT, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);

    this.subType = subType;
  }
}
