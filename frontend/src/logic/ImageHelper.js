/**
 * Removes the Data URL part of a loaded image, making it decodable from Base64.
 * @param {String} loadedImage The loaded image encoded string.
 * @returns {String} The same image string without the Data URL part.
 * @example 
 * const imageEncodedString = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEBLA";
 * const imageAsBase64 = removeDataURL(imageEncodedString);
 * // imageAsBase64 = /9j/4AAQSkZJRgABAQEBLA
 * // imageAsBase64 is now Base64 decodable.
 */
function removeDataURL(loadedImage) {
    return loadedImage.split(',')[1];
}

/**
 * Loads a image and returns it as a Base64 encoded string.
 * @param {File} file The file to load the image from.
 * @returns {Promise<String>} A promise of a Base64 encoded String.
 */
export async function loadImageAsBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            const fileAsBase64 = removeDataURL(e.target.result);
            resolve(fileAsBase64);
        };
        reader.onerror = () => reject(reader.error);
        reader.readAsDataURL(file);
    });
}