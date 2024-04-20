# Pagination without paging http api includes MVVM, Paging, DataBinding, Hilt
The app aims to demonstrate the power of a new paging library combined with data-binding and superior architecture in android app development.

## File Structure
![File Structure in Android Studio](https://i.postimg.cc/s2ndGc0j/Screenshot-from-2024-04-20-12-38-50.png) 

## Folders-
**Comman**  contains standred error adapters and UI.
**Data** has models, repo and pagingnation logics.
**DI** Hilt dependency injection
**Network** has retrofit request apis.
**UI** has activity, adapters, and UI state for handling data and connection status.
**ViewModels** logics to handle data get from repository.


## How Does it Work?

To give overview here I have a flowchart. ![Flowchart](https://i.postimg.cc/sgX09cLT/Mvvm-1.png)
Here paging source class process pulling data with specific config for paging like page number.

 - PagingSource class for the process of pulling the data,responsible for the source of the paginated data.  
 - LoadParam holds the page number to be loaded,
 - @LoadResult as return type. 
 - @getRefreshKey abstract method for subsequent refresh calls to @PagingSource.load().
 Performance matrix memory
 <p float="left">
   <img src="https://i.postimg.cc/TY9SqfsV/Screenshot-from-2022-04-09-17-01-47.png" width="500"  />
   <p> images are rendered</p>
   <img src="https://i.postimg.cc/VkkhK2y3/Screenshot-from-2022-04-09-17-01-26.png" width="500" />
   <p> while Making api request </p>
 </p>
 
## Image Cache
#### Class: ImageLoader in MemoryCache.kt file
- The ImageLoader class is responsible for loading images from a given URL, caching them in memory and on disk, and displaying them in an ImageView.

- Constructor: ImageLoader(context: Context)
- Creates a new instance of the ImageLoader class.

#### Arguments:

- context: The context of the caller. This is used for accessing the cache directory.
- Method: loadBitmap(imageUrl: String, imageView: ImageView)
- Loads an image from a given URL and displays it in an ImageView. The image is first searched in the memory cache, then in the disk cache, and finally downloaded from the network if it’s not found in the caches. The downloaded  
- image is then resized and compressed before being cached and displayed.

#### Arguments:

- imageUrl: The URL of the image to load.
- imageView: The ImageView in which to display the loaded image.
- Method: cancel(imageUrl: String)
- Cancels the loading of an image.

#### Arguments:

- imageUrl: The URL of the image whose loading should be cancelled.
- Method: processBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int, quality: Int): Bitmap
- Resizes and compresses a bitmap.

#### Arguments:

- bitmap: The bitmap to resize and compress.
- newWidth: The new width of the bitmap.
- newHeight: The new height of the bitmap.
- quality: The quality of the compression.
- Returns:

- A new bitmap that is a resized and compressed version of the original bitmap.

##Issue Image were very high size so they were taking huge memory and reduce the performance of UI

#### Before Image Optimization:

Reason: The main reason for this was the use of unoptimized, high-resolution images. Loading these large images into memory for each grid item was a heavy operation, causing the main thread to be overworked. This resulted in a laggy and unresponsive user interface.
Memory usage: The memory usage of the app was high due to the large size of the images being loaded into memory. This could potentially lead to OutOfMemoryError if not handled properly **250 MB memory is being used with 44 Threads** .

<img src="https://i.postimg.cc/yd5MvNH2/Screenshot-from-2024-04-20-11-14-25.png" width="500"  />
<p> images are rendered</p>

#### After Image Optimization:
- Smooth Scrolling: After optimizing the images, the scrolling became much smoother. The frame drops were significantly reduced, leading to a better user experience.
Efficient Memory Usage: By resizing and compressing the images before caching them, the memory usage was greatly reduced. This also helped to avoid OutOfMemoryError.
- Faster Loading Times: Optimized images load faster because there’s less data to process. This resulted in faster loading times for the grid of images **Memory reduced to 106 MB and only 35 Threds are being used**.
- Improved User Experience: Overall, optimizing the images led to an improved user experience. The app became more responsive and less resource-intensive.

<img src="https://i.postimg.cc/BQWWFtNK/optimisation-with-only-reszie-peek.png" width="500"  />
<p> images are rendered</p>

Take a look at code itself.
https://postimg.cc/gallery/qR1psRp
