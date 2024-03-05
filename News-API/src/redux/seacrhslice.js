
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchnews = createAsyncThunk('redux/fetchProducts', async ({wrd}) => {
    const response = await axios.get(`https://newsapi.org/v2/everything?q=${wrd}&language=en&apiKey=60a76d22b1804464ac5c7212a6baf031`); 
    console.log(`${wrd}`);
    return response.data;
});

const searchSlice = createSlice({
  name: 'words',
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchnews.fulfilled, (state, action) => {
        return action.payload;
      });
  },
});

export default searchSlice.reducer;
