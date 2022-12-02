import {configureStore,createSlice} from "@reduxjs/toolkit"

const registration = createSlice({
    name:"registration",
    initialState:[{봄:[],여름:[],가을:[],겨울:[]},{style:[]}],
    reducers:{
        addStyle(state,action){
              state[action.payload.index][action.payload.list].push(action.payload.value)
        },
        deduplicationStyle(state,action){
            state[action.payload.index][action.payload.list].filter((element,index)=>state[action.payload.index][action.payload.list].indexOf(element)===index)
        },
        removeStyle(state,action){
            state[action.payload.index][action.payload.list].splice(state[action.payload.index][action.payload.list].indexOf(action.payload.value),1)
        },
        clearStyle(state,action){
            Object.keys(state[action.payload]).map(item=>state[action.payload][item].splice(0))}
    }})

    export const{addStyle,removeStyle,deduplicationStyle,clearStyle} = registration.actions

    export default configureStore({
        reducer:{
        addStyleList : registration.reducer
        }})