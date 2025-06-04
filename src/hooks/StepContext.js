import { createContext,useContext, useState } from "react";

const StepContext=createContext()
export function StepProvider({children}){
    const [step, setStep]=useState(1)
    return(
        <StepContext value={{step,setStep}}>
            {children}
        </StepContext>
    )
}
export function useStep (){
    return useContext(StepContext)
}