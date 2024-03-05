// productsSlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchProducts = createAsyncThunk('redux/fetchProducts', async () => {
  const response = await axios.get('https://newsapi.org/v2/top-headlines?country=us&apiKey=60a76d22b1804464ac5c7212a6baf031'); // Replace with your API endpoint
  console.log('response');
  return response.data;
});

const productsSlice = createSlice({
  name: 'products',
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.fulfilled, (state, action) => {
        return action.payload;
      });
  },
});

export default productsSlice.reducer;
