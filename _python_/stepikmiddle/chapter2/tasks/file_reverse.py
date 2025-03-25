def flush_buffer(buffer, file):
    line = "".join(reversed(buffer))
    file.write(line)
    file.write("\n")  # Write the line break after the reversed line
    buffer.clear()  # Reset the buffer

def reverse_file(input_file, output_file):
    with open(input_file, "rb") as f, open(output_file, "w", newline="") as w:
        f.seek(0, 2)  # Move to the end of the file
        start_position = f.tell()  # Get the current position at the end of file
        position = start_position
        buffer = []
        prev_char = None  # To track the previous character for handling \r\n

        while position > 0:
            f.seek(position - 1)  # Move to the previous byte
            char = f.read(1)

            if char == b"\n":  # Line feed (LF)
                # If the previous character was \r, we have a CRLF (skip it)
                if prev_char == b"\r":
                    buffer.pop()  # Remove the \r character from the buffer
                flush_buffer(buffer, w)
            elif char == b"\r":  # Carriage return (CR)
                # If it"s a standalone \r, we treat it as a line ending (similar to \n in Unix)
                # Do nothing (just continue), the next loop will handle it as a line break.
                if prev_char != b"\n":  # Not followed by \n, treat it as a line break
                    flush_buffer(buffer, w)
            else:
                # Add the character to the buffer
                buffer.append(char.decode("utf-8"))

            prev_char = char  # Keep track of the previous character
            position -= 1

        # Handle the last line if it"s not empty
        if buffer:
            line = "".join(reversed(buffer))
            w.write(line)

reverse_file("test_files/input.txt", "test_files/output.txt")

# Или простое решение которое загружает сразу весь файл в память:
# with open("test_files/dataset_24465_4.txt", "r") as f, open("test_files/output.txt", "w") as w:
#     for line in reversed(f.readlines()):
#         w.write(line)
