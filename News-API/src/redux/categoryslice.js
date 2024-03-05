// productsSlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchitems = createAsyncThunk('redux/fetchProducts', async ({cat}) => {
    const response = await axios.get(`https://newsapi.org/v2/top-headlines?category=${cat}&language=en&apiKey=60a76d22b1804464ac5c7212a6baf031`); 
    console.log('category fetch');
    return response.data;
});

const categorySlice = createSlice({
  name: 'items',
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchitems.fulfilled, (state, action) => {
        return action.payload;
      });
  },
});

export default categorySlice.reducer;
