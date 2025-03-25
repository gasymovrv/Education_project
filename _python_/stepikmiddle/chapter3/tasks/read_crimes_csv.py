import csv


def most_popular_crime(file_path: str) -> tuple:
    column_name = "Primary Type"

    with open(file_path) as f:
        reader = csv.reader(f)
        header = next(reader)
        index = header.index(column_name)
        crimes_count_by_types = {}
        max_type, max_count = None, 0

        for row in reader:
            if not row[index] or len(row) <= index:
                continue

            crime_type = row[index]
            crimes_count_by_types[crime_type] = crimes_count_by_types.get(crime_type, 0) + 1
            count = crimes_count_by_types[crime_type]

            if count > max_count:
                max_type = crime_type
                max_count = count

        return max_type, max_count


print(most_popular_crime("test_files/Crimes.csv"))
