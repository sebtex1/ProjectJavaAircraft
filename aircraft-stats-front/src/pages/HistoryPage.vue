<template>
    <div>
        <label for="time">Time: </label>
        <select style="margin-bottom: 20px;" id="time" name="time">
        <option value="now">now</option>
        <option value="1650540944">{{ new Date(1650540944000) }}</option>
        </select>
    </div>
</template>

<script>
export default {
    
}
</script>