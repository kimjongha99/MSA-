import React, { ChangeEvent, Dispatch, KeyboardEvent, MutableRefObject, SetStateAction, forwardRef, useRef } from 'react';
import './style.css';
import {Simulate} from "react-dom/test-utils";
import change = Simulate.change;
import * as events from "events";

//          interface: Input 상자 컴포넌트 Props          //
interface Props {
    label : string;
    type: 'text' | 'password';
    error: boolean;

    placeholder: string;
    value: string;
    setValue:Dispatch<SetStateAction<string>>;

    icon?: string;
    onButtonClick?: ()=>void;

    errorMessage?: string;

    onKeyDown? : (event: KeyboardEvent<HTMLInputElement>) => void;

}

//          component: Input 상자 컴포넌트          //
const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {
  // status:properties  //
    const {label ,type,error,placeholder,value,icon,errorMessage } =props;

    const {setValue, onButtonClick, onKeyDown} =props;
    // event handler  input 값 변경 이벤트 처리함수
    const  onChangeHandler = (event: ChangeEvent<HTMLInputElement>)=>{
        const  value  = event.target.value;

        setValue(value);
    }

    // event handler  input 값 변경 이벤트 처리함수
    const onKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>)=>{
        if (!onKeyDown)return;
        onKeyDown(event);
    }

  // render: Input 상자 렌더링 //
  return (
      <div className='inputbox'>
        <div className='inputbox-label'>{label}</div>
        <div className={error ? 'inputbox-container-error':'inputbox-container'}>
          <input ref={ref} type={type} className='input' placeholder={placeholder} value={value} onChange={onChangeHandler} onKeyDown={onKeyDownHandler}/>

            {onButtonClick !== undefined &&(
                <div className='icon-button'>
                    {icon !== undefined &&  <div className={`icon ${icon}`}> </div>}

                </div>
            )}
            {errorMessage !== undefined &&<div className='inputbox-message'>{errorMessage}</div>
            }
        </div>
      </div>
  )
});

export default InputBox;
