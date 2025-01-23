//1.Create a context
//3.Put some state in the Context
//2.share the created context with other components

import { createContext, useState ,useContext} from "react";



export const useAuth=()=>useContext(AuthContext)


export const AuthContext=createContext()


export default function AuthProvider({children}){


    const [number,setnumber]=useState(0)
    const [isAuthenticated,setAuthenticated]=useState(false)
    return(
        <AuthContext.Provider value={{number}}>
            {children}
        </AuthContext.Provider>
    )
}