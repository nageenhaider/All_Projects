import { configureStore } from '@reduxjs/toolkit';
import productsReducer from './slice';
import categoryReducer from './categoryslice'
import searchReducer from './seacrhslice'
const store = configureStore({
  reducer: {
    products: productsReducer,
    items:categoryReducer,
    words:searchReducer,
  },
});

export default store;