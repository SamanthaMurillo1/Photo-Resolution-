This project leverages a quadrant tree data structure to manipulate the resolution of digital images by subdividing or merging image regions. The tree nodes, represented by the QTreeNode class, encapsulate spatial and color information of image segments.

The QTreeNode class is designed to support efficient representation and processing of image data:

Key Features:
Hierarchical Structure: Each node maintains references to its parent and up to four child nodes, enabling flexible division of image regions.
Spatial and Color Properties: Nodes store coordinates, size, and color information for precise resolution control.
Resolution Adjustment: Supports both increasing (subdividing nodes) and decreasing (merging nodes) image resolution by dynamically modifying the tree structure.
Efficient Point Containment: Includes methods to determine whether specific coordinates fall within a node's boundaries, enhancing spatial operations. The implementation prioritizes modularity, making it suitable for integration into larger image-processing pipelines.
